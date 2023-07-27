package com.feuji.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feuji.blog.entity.Comment;

/**
 * @author Priya Patel
 * This interface is for database connection of comment resource
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>
{
	

}
