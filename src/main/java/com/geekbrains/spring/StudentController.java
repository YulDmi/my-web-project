package com.geekbrains.spring;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping("/students")
    public String getAll(Model model){
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "students";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute  Student student) {
        studentService.save(student);

        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute(studentService.findById(id).get());
        return "student_change";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        studentService.delete(id);
        return "redirect:/students";
    }


}
