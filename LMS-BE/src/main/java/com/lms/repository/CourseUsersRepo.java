package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.CourseUsers;

public interface CourseUsersRepo extends JpaRepository<CourseUsers, Integer> {

	CourseUsers findByuserEmail(String userEmail);

	boolean existsByuserEmail(String userEmail);

}
