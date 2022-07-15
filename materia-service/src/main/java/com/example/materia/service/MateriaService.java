package com.example.materia.service;

import com.example.materia.exceptions.NoEntityException;
import com.example.materia.model.Materia;
import com.example.materia.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;

    public Materia createMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }

    public Materia findById(Long id) throws NoEntityException {
        return materiaRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Materia con " + id));
    }

    public List<Materia> finByStudentId(Long studentId){
        return materiaRepository.findByStudentId(studentId);
    }

    public Materia updateMateria(Materia materia) throws NoEntityException {
        Materia materiaOld = materiaRepository.findById(materia.getId()).orElseThrow(
                () -> new NoEntityException("No existe Materia con " + materia.getId()));
        return materiaRepository.save(materiaOld);
    }

    public void deleteMateria(Long id) throws NoEntityException {
        Materia materia = materiaRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Materia con " + id));
        materiaRepository.delete(materia);
    }

}
