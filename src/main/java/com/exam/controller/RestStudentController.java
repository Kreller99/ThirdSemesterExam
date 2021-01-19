package com.exam.controller;

import com.exam.model.Student;
import com.exam.model.Supervisor;
import com.exam.repository.StudentRepo;
import com.exam.repository.SupervisorRepo;
import com.exam.service.StudentService;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class RestStudentController{


    StudentRepo studentRepo;
    SupervisorRepo supervisorRepo;
    StudentService studentService;

    public RestStudentController(StudentRepo studentRepo, StudentService studentService, SupervisorRepo supervisorRepo) {
        this.studentRepo = studentRepo;
        this.studentService = studentService;
        this.supervisorRepo = supervisorRepo;
    }

    // Metode, der bliver brugt til at finde alle studerende.
    // Her laves en Iterable, da vi skal itererer igennem en liste af studerende.
    @GetMapping("/student")
    public Iterable<Student> findAll(){

        // Vi returnerer til sidst StudentRepo's metode "findAllStudents()",
        // som giver en liste af studerende
        return studentRepo.findAll();
    }


    // CrossOrigin gør, så kun visse personer kan tilgå createStudent metoden,
    // hvor * vil sige, at alle kan oprette en studerende.
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    // PostMapping bruges til at oprette en student.
    // Her siger vi, hvor den kommer fra (Student), og at den bruger JSon som medietype.
    @PostMapping(value = "/student", consumes = MediaType.APPLICATION_JSON_VALUE)
    // Vi laver en ResponseEntity, da vi skal have en respons tilbage, om en student er blevet oprettet,
    // hvoraf vi requester en student.
    public ResponseEntity<String> createStudent(@RequestBody Student stud){
        Student student = new Student(stud.getName(), stud.getEmail());

        // Vi sætter en supervisor på en studerende, og gemmer derefter den studerende ved at kalde
        // studentRepo metoden Save, hvor den tager en student som parametre.
        student.setSupervisor(stud.getSupervisor());
        studentRepo.save(student);

        //Returnerer en post created status.
        return ResponseEntity.status(201).header("Location", "/student/" + student.getId()).body("{'Msg' : 'post created'}");
    }


    // Bruges til at finde en studerende ud fra et givent ID.
    // Hvis den studerende findes, returnere Status OK med den studerende i body.
    // Hvis den studerende ikke findes, returneres fejl status 404.
    @GetMapping("/student/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable Long id){
        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent()){
            return ResponseEntity.status(200).body(student);
        } else{
            return ResponseEntity.status(404).body(student);
        }
    }
    // Bruges til at updatere en studerende.
    // Her tages ID som parametre samt en studerende som et RequestBody.
    @PutMapping("/student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") Long id, @RequestBody Student stud){
        // Ser, om en studerende findes. Hvis den ikke findes, returnere den fejlstatus 404.
        Optional<Student> student = studentRepo.findById(id);
        if (!student.isPresent()){
            return ResponseEntity.status(404).body("{'msg':'Not found'");
        }
        // Hvis den findes, gemmes den opdaterede studerende i databasen,
        // ved at overskride den nuværende studerende.
        studentRepo.save(stud);
        return ResponseEntity.status(200).body("{'msg': 'Updated'}");
    }

    // Sletter en studerende ud fra et givent id. Hvis den studerende ikke findes,
    // returnes fejlstatus 404.
    // Hvis den studerende findes, slettes den ud fra ID'et.
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        Optional<Student> student = studentRepo.findById(id);

        if(!student.isPresent()){
            return ResponseEntity.status(404).body("{'msg':'Not found'}");
        }

        studentRepo.deleteById(id);

        return ResponseEntity.status(200).body("{'msg':'Deleted'}");
    }



}
