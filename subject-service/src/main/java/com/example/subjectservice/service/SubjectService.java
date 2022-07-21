package com.example.subjectservice.service;

import com.example.subjectservice.exceptions.NoEntityException;
import com.example.subjectservice.model.Subject;
import com.example.subjectservice.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject findById(Long id) throws NoEntityException {
        return subjectRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Materia con " + id));
    }

    public List<Subject> findByStudentId(Long studentId){
        return subjectRepository.findByStudentId(studentId);
    }

    public Subject updateSubject(Subject subject) throws NoEntityException {
        Subject materiaOld = subjectRepository.findById(subject.getId()).orElseThrow(
                () -> new NoEntityException("No existe Materia con " + subject.getId()));
        return subjectRepository.save(materiaOld);
    }

    public void deleteSubject(Long id) throws NoEntityException {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Materia con " + id));
        subjectRepository.delete(subject);
    }

}
