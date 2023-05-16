/**
 * 
 */
package com.alok91340.epam.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alok91340.epam.entities.User;

/**
 * @author alok91340
 *
 */
public interface UserRepo extends JpaRepository<User,Integer>{
	User findByEmail(String email);

    Optional<User> findByNameOrEmail(String name, String email);

    Optional<User> findByName(String name);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
}
