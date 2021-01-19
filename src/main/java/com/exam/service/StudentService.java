package com.exam.service;

import com.exam.model.Student;
import com.exam.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    // Her implementere vi studentRepo's metoder, og indsætter de værdier, vi skal bruge,
    // for at kunne bruge de forskellige metoder.
    public Set<Student> findAllStudents(){
        Set<Student> students = new HashSet<>();

        for(Student student : studentRepo.findAll()){
            students.add(student);
        }

        return students;
    }

    public void createStudent(Student student){
        studentRepo.save(student);
    }

    public Student findById(Long id){
        Optional<Student> studentOptional = studentRepo.findById(id);

        if(!studentOptional.isPresent()){
            throw new RuntimeException("Student not found");
        }

        return studentOptional.get();
    }

    public void updateStudent(Student student){
        studentRepo.save(student);
    }

    public void deleteStudent(Long id){
        studentRepo.deleteById(id);


    }



}
