package com.example.materia.controller;

import com.example.materia.exceptions.NoEntityException;
import com.example.materia.model.Materia;
import com.example.materia.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {
    @Autowired
    private MateriaService materiaService;

    @PostMapping
    public ResponseEntity<Materia> createMateria(@RequestBody Materia materia){
        Materia materiaNew = materiaService.createMateria(materia);
        return new ResponseEntity<Materia>(materiaNew, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Materia>> getMaterias(){
        return ResponseEntity.ok(materiaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMateriaById(@PathVariable("id") Long id) {

        try {
            Materia materia = materiaService.findById(id);
            return ResponseEntity.ok(materia);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Materia No encontrada ");
        }
    }

    @GetMapping("/bystudent/{studentid}")
    public ResponseEntity<?> finByStudentId(@PathVariable("studentid") Long studentId){
        return ResponseEntity.ok().body(materiaService.finByStudentId(studentId));
    }

    @PutMapping
    public ResponseEntity<Materia> updateMateria(@RequestBody Materia materia){
        Materia materiaNew = new Materia();
        try {
            materiaNew = materiaService.updateMateria(materia);
            return ResponseEntity.ok(materiaNew);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(materiaNew);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMateria(@PathVariable("id") Long id){
        try {
            materiaService.deleteMateria(id);
            return ResponseEntity.ok("Materia Eliminado");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Materia No Eliminada");
        }
    }

}
