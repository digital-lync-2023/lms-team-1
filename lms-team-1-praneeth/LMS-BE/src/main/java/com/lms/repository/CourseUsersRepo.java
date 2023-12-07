package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.CourseUsers;

public interface CourseUsersRepo extends JpaRepository<CourseUsers, Integer> {

	CourseUsers findByuseremail(String email);

	boolean existsByuseremail(String email);

}
