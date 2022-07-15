package com.example.student.feignclients;

import com.example.student.model.Materia;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="materia-microservice")
public interface MateriaFeignClient {
    @PostMapping("/materias")
    public Materia createMateria(@RequestBody Materia materia);

    @GetMapping("/exams/bystudent/{studentid}")
    public ResponseEntity<?> finByStudentId(@PathVariable("studentid") Long studentId);

}
