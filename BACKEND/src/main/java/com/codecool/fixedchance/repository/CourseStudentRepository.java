package com.codecool.fixedchance.repository;

import com.codecool.fixedchance.domain.CourseStudent;
import com.codecool.fixedchance.domain.CourseStudentIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, CourseStudentIdentity> {
}
