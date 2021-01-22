package com.exam.controller;

import com.exam.model.Student;
import com.exam.repository.StudentRepo;
import com.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    StudentService studentService;

    // Bruges til at vise startsiden. Her vises en liste af studerende,
    // hvor man også kan oprette, opdatere og slette en studerende.
    @GetMapping("/")
    public String index(Model model, Student student){
        model.addAttribute("students", studentService.findAllStudents());

        return "index";
    }

    @PostMapping("create")
    public String createStudentPost(Student student){
        studentService.createStudent(student);

        return "redirect:/";
    }

    @GetMapping("update")
    public String updateStudentGet(@RequestParam Long id, Model model, Student student){
        model.addAttribute("students", studentService.findById(id));

        return "update";
    }

    @PostMapping("update")
    public String updateStudentPost(Student student){
        studentService.updateStudent(student);

        return "redirect:/";
    }

    @GetMapping("delete")
    public String deleteStudent(@RequestParam Long id){
        studentService.deleteStudent(id);
        return "redirect:/";
    }






}
