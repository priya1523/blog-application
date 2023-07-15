package com.feuji.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feuji.blog.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>
{

}
