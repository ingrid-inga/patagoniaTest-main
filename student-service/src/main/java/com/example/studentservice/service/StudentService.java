package com.example.studentservice.service;

import com.example.studentservice.exceptions.NoEntityException;
import com.example.studentservice.feignclients.SubjectFeignClient;
import com.example.studentservice.model.Student;
import com.example.studentservice.model.Subject;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SubjectFeignClient subjectFeignClient;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }


    public Subject createSubject(@RequestBody Subject subject){
        return subjectFeignClient.createSubject(subject);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) throws NoEntityException {
        return studentRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Student con " + id));
    }

    public List<Subject> getSubjects(Long studentId){
        return (List<Subject>) subjectFeignClient.findByStudentId(studentId).getBody();
    }

    public Student updateStudent(Student student) throws NoEntityException {
        Student studentNew = studentRepository.findById(student.getId()).orElseThrow(
                () -> new NoEntityException("No existe Student con " + student.getId()));
        studentNew.setSurname(student.getSurname());
        studentNew.setName(student.getName());
        studentNew.setBirthday(student.getBirthday());
        return studentRepository.save(studentNew);
    }

    public void deleteStudent(Long id) throws NoEntityException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Student con " + id));
        studentRepository.delete(student);
    }

}
