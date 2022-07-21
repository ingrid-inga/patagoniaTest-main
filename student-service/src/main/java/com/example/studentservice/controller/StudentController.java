package com.example.studentservice.controller;

import com.example.studentservice.exceptions.NoEntityException;
import com.example.studentservice.model.Student;
import com.example.studentservice.model.Subject;
import com.example.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student studentNew = studentService.createStudent(student);
        return new ResponseEntity<Student>(studentNew, HttpStatus.CREATED);
    }

    @PostMapping(value ="/createsubject/{studentId}")
    public ResponseEntity<Subject> createSubject(@PathVariable("studentId") Long studentId , @RequestBody Subject subject){
        subject.setStudentId(studentId);
        return ResponseEntity.ok().body(studentService.createSubject(subject));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {

        try {
            Student student = studentService.findById(id);
            return ResponseEntity.ok(student);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Student No encontrado ");
        }
    }

    @GetMapping("/subjectsbystudent/{id}")
    public ResponseEntity<Map<String,Object>> getSubjects(@PathVariable("id") Long studentId){
        List<Subject> subjects = studentService.getSubjects(studentId);
        Map<String,Object> studentMap = new HashMap<>();
        if ( subjects.isEmpty()){
            studentMap.put("El estudiante " + studentId , " No tiene Materias " );
        }
        studentMap.put("El estudiante " + studentId , subjects);
        return ResponseEntity.ok().body(studentMap);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Student studentNew = new Student();
        try {
            studentNew = studentService.updateStudent(student);
            return ResponseEntity.ok(studentNew);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(studentNew);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id){
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student Eliminado");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Student No Eliminado");
        }
    }
}
