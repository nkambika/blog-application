package com.myprojects.repositories;

import com.myprojects.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByMobile(String mobile);
    boolean existsByEmail(String email);

    @Query("select u from User u where u.email like %:search% or u.mobile like %:search% order by id desc")
    Page<User> findByWildSearch(@Param("search") String search, Pageable pageable);
}
