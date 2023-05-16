/**
 * 
 */
package com.alok91340.epam.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alok91340.epam.entities.Comment;
import com.alok91340.epam.entities.Post;

/**
 * @author alok91340
 *
 */
public interface CommentRepo extends JpaRepository<Comment,Integer>{
	Set<Comment> findByPost(Post post);
}
