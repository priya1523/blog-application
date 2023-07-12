package com.feuji.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feuji.blog.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

}
