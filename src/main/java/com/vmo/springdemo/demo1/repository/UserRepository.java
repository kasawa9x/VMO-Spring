package com.vmo.springdemo.demo1.repository;

import com.vmo.springdemo.demo1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  User findByName(String name);
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
