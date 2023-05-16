/**
 * 
 */
package com.alok91340.epam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alok91340.epam.entities.Category;
import com.alok91340.epam.entities.Post;
import com.alok91340.epam.entities.User;

/**
 * @author alok91340
 *
 */
public interface PostRepo extends JpaRepository<Post,Integer>{

	List<Post> findByUser(User user);
	
	Post findByPostId(Integer postId);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
