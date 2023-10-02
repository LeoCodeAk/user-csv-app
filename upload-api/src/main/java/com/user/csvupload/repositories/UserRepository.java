package com.user.csvupload.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.user.csvupload.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // We can add custom queries or methods here if needed
}
