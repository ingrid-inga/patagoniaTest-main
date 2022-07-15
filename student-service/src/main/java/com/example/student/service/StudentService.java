package com.example.student.service;


import com.example.student.exceptions.NoEntityException;
import com.example.student.feignclients.MateriaFeignClient;
import com.example.student.model.Materia;
import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MateriaFeignClient materiaFeignClient;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }


    public Materia createMateria(@RequestBody Materia materia){
        return materiaFeignClient.createMateria(materia);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) throws NoEntityException {
        return studentRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Student con " + id));
    }

    public List<Materia> getMaterias(Long studentId){
        return (List<Materia>) materiaFeignClient.finByStudentId(studentId).getBody();
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

    public int calcularEdad(Long id) throws NoEntityException {
        Optional<Student> student = this.studentRepository.findById(id);
        return Period.between(student.orElseThrow(() -> new NoEntityException("No existe Student con " + id)).getBirthday(),
                LocalDate.now()).getYears();

    }

}
