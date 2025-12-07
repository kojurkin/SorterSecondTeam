package org.example.student;

public class StudentBuilder {
    public Integer groupNumber;
    public Double averageScore;
    public Integer studentBookNumber;

    public StudentBuilder setGroupNumber(Integer groupNumber) {
        if (groupNumber <= 0) {
            throw new IllegalArgumentException("Номер группы может быть только положительным числом.");
        }
        this.groupNumber = groupNumber;
        return this;
    }

    public StudentBuilder setAverageScore(Double averageScore) {
        if (averageScore < 2.0 || averageScore > 5.0) {
            throw new IllegalArgumentException("Средняя оценка должны быть в диапазоне от 2 до 5.");
        }
        this.averageScore = averageScore;
        return this;
    }

    public StudentBuilder setStudentBookNumber(Integer studentBookNumber) {
        if (groupNumber <= 0) {
            throw new IllegalArgumentException("Номер зачетной книжки может быть только положительным числом.");
        }
        this.studentBookNumber = studentBookNumber;
        return this;
    }

    public Student build() {
        if (groupNumber == null || averageScore == null || studentBookNumber == null) {
            throw new IllegalStateException("Все поля для класса студент (номер группы, средний балл и номер зачетной книжки) должны быть указаны");
        }
        return new Student(groupNumber, averageScore, studentBookNumber);
    }
}
