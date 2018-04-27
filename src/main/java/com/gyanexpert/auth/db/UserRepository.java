package com.gyanexpert.auth.db;

import org.springframework.data.repository.CrudRepository;
/*
 * Repository class to get user by username for authentication
 */
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
}
