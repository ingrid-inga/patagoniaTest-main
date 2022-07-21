package com.example.subjectservice.controller;

import com.example.subjectservice.exceptions.NoEntityException;
import com.example.subjectservice.model.Subject;
import com.example.subjectservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        Subject subjectNew = subjectService.createSubject(subject);
        return new ResponseEntity<Subject>(subjectNew, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Subject>> getSubjects(){
        return ResponseEntity.ok(subjectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMateriaById(@PathVariable("id") Long id) {

        try {
            Subject subject = subjectService.findById(id);
            return ResponseEntity.ok(subject);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Materia No encontrada ");
        }
    }

    @GetMapping("/bystudent/{studentid}")
    public ResponseEntity<?> finByStudentId(@PathVariable("studentid") Long studentId){
        return ResponseEntity.ok().body(subjectService.findByStudentId(studentId));
    }

    @PutMapping
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject){
        Subject subjectNew = new Subject();
        try {
            subjectNew = subjectService.updateSubject(subject);
            return ResponseEntity.ok(subjectNew);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(subjectNew);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") Long id){
        try {
            subjectService.deleteSubject(id);
            return ResponseEntity.ok("Materia Eliminada");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Materia No Eliminada");
        }
    }

}
