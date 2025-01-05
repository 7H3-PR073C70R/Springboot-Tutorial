package com.protector.SpringDataJPAExample;

import com.protector.SpringDataJPAExample.model.Student;
import com.protector.SpringDataJPAExample.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class SpringDataJpaExampleApplication {

	public static void main(String[] args) {
	  final ApplicationContext context =  SpringApplication.run(SpringDataJpaExampleApplication.class, args);
		Student s1 = context.getBean(Student.class);
		Student s2 = context.getBean(Student.class);
		Student s3 = context.getBean(Student.class);

		StudentRepository repository = context.getBean(StudentRepository.class);

//		s1.setRollNumber(1);
//		s1.setName("Protector");
//		s1.setMarks(100);
//
//		s2.setRollNumber(2);
//		s2.setName("Esther");
//		s2.setMarks(98);
//
//		s3.setRollNumber(3);
//		s3.setName("Dee");
//		s3.setMarks(96);

		//? Save can also be used to update
//		repository.save(s1);
//		repository.save(s2);
//		repository.save(s3);

//		Optional<Student> s = repository.findById(1);
//		System.out.println(s.orElse(new Student()));

		System.out.println(repository.findByName("Protector"));
		System.out.println(repository.findByMarks(10));
		System.out.println(repository.findByMarksGreaterThan(90));
	}

}
