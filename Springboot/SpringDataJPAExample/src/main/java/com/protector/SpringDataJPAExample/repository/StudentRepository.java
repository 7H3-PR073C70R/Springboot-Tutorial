package com.protector.SpringDataJPAExample.repository;

import com.protector.SpringDataJPAExample.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.name = ?1")
    List<Student> findByName(String name);

    List<Student> findByMarks(int mark);
    List<Student> findByMarksGreaterThan(int mark);
}
