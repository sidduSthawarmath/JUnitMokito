package com.siddu.repo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siddu.domain.Student;

@Repository
@Transactional
public interface StudentRepo extends CrudRepository<Student,Long> {

	Student findByRollNum(String rollNum);

}
