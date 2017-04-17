package com.ooad.project.repository;

import com.ooad.project.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUserId(Long userId);
	User findByUserName(String userName);
	@Transactional
	void deleteByUserName(String userName);
}
