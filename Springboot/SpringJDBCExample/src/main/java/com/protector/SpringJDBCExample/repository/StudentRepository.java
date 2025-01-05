package com.protector.SpringJDBCExample.repository;

import com.protector.SpringJDBCExample.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class StudentRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student student) {
        final String sql = "INSERT INTO student (rollno, name, marks) values (?, ?, ?)";
        int rolls = jdbc.update(sql, student.getRowNumber(), student.getName(), student.getMarks());
        System.out.println(rolls + " effected");
    }

    public List<Student> findAll() {
        final String sql = "SELECT * FROM student ORDER BY rollno";
        return  jdbc.query(sql, ( rs,  rowNum) -> {
            final Student student = new Student();
            student.setRowNumber(rs.getInt("rollno"));
            student.setName(rs.getString("name"));
            student.setMarks(rs.getInt("marks"));

            return  student;
        });
    }
}


//        RowMapper<Student> mapper = ( rs,  rowNum) -> {
//            final Student student = new Student();
//            student.setRowNumber(rs.getInt("rollno"));
//            student.setName(rs.getString("name"));
//            student.setMarks(rs.getInt("marks"));
//
//            return  student;
//        };