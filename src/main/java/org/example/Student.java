package org.example;

public class Student {
    private Integer groupNumber;
    private Double averageScore;
    private Integer studentBookNumber;

    public Student(Integer groupNumber, Double averageScore, Integer studentBookNumber) {
        this.groupNumber = groupNumber;
        this.averageScore = averageScore;
        this.studentBookNumber = studentBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Номер группы:" + groupNumber +
                ", Средний балл: " + averageScore +
                ", Номер зачетной книжки: " + studentBookNumber +
                '}';
    }
}
