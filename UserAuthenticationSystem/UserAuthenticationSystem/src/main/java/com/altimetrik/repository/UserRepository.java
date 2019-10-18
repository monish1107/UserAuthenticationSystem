package com.altimetrik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.altimetrik.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
