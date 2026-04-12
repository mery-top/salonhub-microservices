package com.meerthika.service;


import com.meerthika.payload.dto.SignupDTO;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Data
@RequiredArgsConstructor
public class KeycloakService {
    static Dotenv dotenv = Dotenv.load();
    private static final String KeycloakClientSecret = dotenv.get("KEYCLOAK_CLIENT_SECRET");
    private static final String KeycloakClientId = dotenv.get("KEYCLOAK_CLIENT_ID");
    public void test(){
        System.out.println(KeycloakClientSecret);
    }

    private static final String KEYCLOAK_BASE_URL="http://localhost:8080";
    private static final String KEYCLOAK_ADMIN_API= KEYCLOAK_BASE_URL+"/admin/realms/master/users";
    private static final String TOKEN_URL=KEYCLOAK_BASE_URL+"/realms/master/protocol/openid-connect/token";
    private static final String CLIENT_ID=KeycloakClientId;
    private static final String CLIENT_SECRET=KeycloakClientSecret;


    private static final String GRANT_TYPE="password";
    private static final String scope = "openid profile email";
    private static final String username="admin";
    private static final String password= "admin";
    private static final String clientId="5de187fe-b51b-4c44-8454-a61912c5a087";

    private final RestTemplate restTemplate;

    public void createUser(SignupDTO signupDTO) throws Exception{

    }






}
