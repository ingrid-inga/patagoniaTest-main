package com.example.student.feignclients;

import com.example.student.model.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="subject-service")
@RequestMapping("/subjects")

public interface SubjectFeignClient {

    @PostMapping()
     public Subject createSubject(@RequestBody Subject subject);


    @GetMapping("/bystudent/{studentId}")
    public ResponseEntity<?> findByStudentId(@PathVariable("studentId")Long studentId);
}
