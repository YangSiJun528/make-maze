package com.example.makemaze.repository;

import com.example.makemaze.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
