package com.example.restservice.repos;

import com.example.restservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByNickname(String nickname);
}
