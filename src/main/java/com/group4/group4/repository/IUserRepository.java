package com.group4.group4.repository;

import com.group4.group4.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
<<<<<<< HEAD
    UserEntity findByEmail(String email);
=======
    UserEntity findById(long id);
>>>>>>> 0559d501194d17ed51f5cb2001cd4f4dc28ed769
}
