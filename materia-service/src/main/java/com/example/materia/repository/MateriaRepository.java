package com.example.materia.repository;

import com.example.materia.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    List<Materia> findByStudentId(Long studentId);

}
