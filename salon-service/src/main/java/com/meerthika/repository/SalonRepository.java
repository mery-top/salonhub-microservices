package com.meerthika.repository;

import com.meerthika.modal.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Long> {

    //here the function name should be in the exact format
    Salon findByOwnerId(Long id);





    @Query(
            "select s from Salon s where" +
                    "(lower(s.city) like lower(concat('%', :keyword, '%')) OR " +
                    "lower(s.name) like lower(concat('%', :keyword, '%')) OR " +
                    "lower(s.address) like lower(concat('%', :keyword, '%')))"

    )
    List<Salon> searchSalons(@Param("keyword") String keyword);

}
