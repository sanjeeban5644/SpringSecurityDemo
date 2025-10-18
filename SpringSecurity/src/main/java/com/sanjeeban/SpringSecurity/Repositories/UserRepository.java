package com.sanjeeban.SpringSecurity.Repositories;


import com.sanjeeban.SpringSecurity.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long>{

    @Query("SELECT T FROM UserEntity T WHERE T.username=:username")
    public Optional<UserEntity> findByUsername(@Param("username") String username);

}
