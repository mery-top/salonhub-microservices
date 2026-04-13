package com.meerthika.service;


import com.meerthika.payload.dto.*;
import com.meerthika.payload.response.TokenResponse;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.MultivaluedHashMap;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Data
@RequiredArgsConstructor
public class KeycloakService {
    static Dotenv dotenv = Dotenv.load();
    private static final String KeycloakClientSecret = dotenv.get("KEYCLOAK_CLIENT_SECRET");
    private static final String KeycloakClientId = dotenv.get("KEYCLOAK_CLIENT_ID");

    public void test() {
        System.out.println(KeycloakClientSecret);
    }

    private static final String KEYCLOAK_BASE_URL = "http://localhost:8080";
    private static final String KEYCLOAK_ADMIN_API = KEYCLOAK_BASE_URL + "/admin/realms/master/users";
    private static final String TOKEN_URL = KEYCLOAK_BASE_URL + "/realms/master/protocol/openid-connect/token";
    private static final String CLIENT_ID = KeycloakClientId;
    private static final String CLIENT_SECRET = KeycloakClientSecret;


    private static final String GRANT_TYPE = "password";
    private static final String scope = "openid profile email";
    private static final String username = "zosh";
    private static final String password = "admin";
    //put the correct client id
    private static final String clientId = "bcd0935c-93b8-4ac3-bc7e-c96361575415";

    private final RestTemplate restTemplate;

    public void createUser(SignupDTO signupDTO) throws Exception {

        String ACCESS_TOKEN = getAdminAccessToken(username, password, GRANT_TYPE, null).getAccessToken();
        Credential credential = new Credential();
        credential.setTemporary(false);
        credential.setType("password");
        credential.setValue(signupDTO.getPassword());


        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(signupDTO.getUsername());
        userRequest.setEmail(signupDTO.getEmail());
        userRequest.setEnabled(true);
        userRequest.setLastName(signupDTO.getLastName());
        userRequest.setFirstName(signupDTO.getFirstName());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(ACCESS_TOKEN);

        HttpEntity<UserRequest> requestEntity = new HttpEntity<>(userRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                KEYCLOAK_ADMIN_API,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("user created successfully");

            KeycloakUserDTO user = fetchFirstUserByUsername(signupDTO.getUsername(), ACCESS_TOKEN);
            KeycloakRole role = getRoleByName(clientId, ACCESS_TOKEN, signupDTO.getRole().toString());

            List<KeycloakRole> roles = new ArrayList<>();
            roles.add(role);

            assignRoleToUser(
                user.getId(),
                    clientId,
                    roles,
                    ACCESS_TOKEN
            );
        }else{
            System.out.println("user creation failed");
            throw new Exception(response.getBody());
        }
    }

    public TokenResponse getAdminAccessToken(String username,
                                             String password,
                                             String grantType,
                                             String refreshToken
    ) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        //one map with many values one key
        MultivaluedMap<String, String> requestBody = new MultivaluedHashMap<>();
        requestBody.add("grant_type", GRANT_TYPE);
        requestBody.add("username", username);
        requestBody.add("password", password);
        requestBody.add("refresh_token", refreshToken);
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("client_secret", CLIENT_SECRET);
        requestBody.add("scope", scope);

        HttpEntity<MultivaluedMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<TokenResponse> response = restTemplate.exchange(
                TOKEN_URL,
                HttpMethod.POST,
                requestEntity,
                TokenResponse.class
        );

        if(response.getStatusCode()==HttpStatus.OK && response.getBody() !=null){
            return response.getBody();
        }

        throw new Exception("failed to get access token");

    }

    public KeycloakRole getRoleByName(
            String clientId, String token, String role
    ){

        String url = KEYCLOAK_BASE_URL+"/admin/realms/master/clients/"+clientId+"/roles/"+role;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer"+token);
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<KeycloakRole> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                KeycloakRole.class
        );

        return response.getBody();

    }


    public KeycloakUserDTO fetchFirstUserByUsername(String username, String token) throws Exception {
        String url = KEYCLOAK_BASE_URL+"/admin/realms/master/users?username="+username;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<KeycloakUserDTO[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                KeycloakUserDTO[].class
        );

        KeycloakUserDTO[] users = response.getBody();
        if(users!=null && users.length>0){
            return users[0];
        }

        throw new Exception("user not found with username"+ username);

    }

    public void assignRoleToUser(
            String userId,
            String clientId,
            List<KeycloakRole> roles,
            String token
    ){

    }
}



