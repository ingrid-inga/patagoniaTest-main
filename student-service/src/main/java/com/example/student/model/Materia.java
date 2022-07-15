package com.example.student.model;

import java.time.LocalDate;

public class Materia {
    private Long id;
    private LocalDate materiaDate;
    private Float score;
    private Long studentId;

    public Materia() {
    }

    public Materia(Long id, LocalDate materiaDate, Float score, Long studentId) {
        this.id = id;
        this.materiaDate = materiaDate;
        this.score = score;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMateriaDate() {
        return materiaDate;
    }

    public void setMateriaDate(LocalDate materiaDate) {
        this.materiaDate = materiaDate;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", materiaDate=" + materiaDate +
                ", score=" + score +
                ", studentId=" + studentId +
                '}';
    }
}
