package com.ooad.project.repository;

import com.ooad.project.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUserId(Long userId);
	User findByUserName(String userName);
	@Query(
			value = "select * from #{#entityName}",
			nativeQuery = true
	)
	List<User> findAllUsers();
}
