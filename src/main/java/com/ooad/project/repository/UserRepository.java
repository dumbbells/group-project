package com.ooad.project.repository;

import com.ooad.project.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUserId(Long userId);
	User findByUserName(String userName);
}
