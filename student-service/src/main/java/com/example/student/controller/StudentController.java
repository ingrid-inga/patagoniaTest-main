package com.example.student.controller;

import com.example.student.exceptions.NoEntityException;
import com.example.student.model.Materia;
import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student studentNew = studentService.createStudent(student);
        return new ResponseEntity<Student>(studentNew, HttpStatus.CREATED);
    }

    @PostMapping(value ="/createmateria/{studentId}")
    public ResponseEntity<Materia> createExam(@PathVariable("studentId") Long studentId , @RequestBody Materia materia){
        materia.setStudentId(studentId);
        return ResponseEntity.ok().body(studentService.createMateria(materia));
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
            //return new ResponseEntity<>("Student No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Student No encontrado ");
        }
    }

    @GetMapping("/examsbystudent/{id}")
    public ResponseEntity<Map<String,Object>> getExams(@PathVariable("id") Long studentId){
        List<Materia> materias = studentService.getMaterias(studentId);
        Map<String,Object> studentMap = new HashMap<>();
        if ( materias.isEmpty()){
            studentMap.put("El estudiante " + studentId , " No tiene Materias " );
        }
        studentMap.put("El estudiante " + studentId , materias);
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
            //return new ResponseEntity<>("Student No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Student No Eliminado");
        }
    }
}
