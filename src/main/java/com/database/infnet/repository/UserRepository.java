package com.database.infnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.database.infnet.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}