package org.example.student;

public class Student {
    public Integer groupNumber;
    public Double averageScore;
    public Integer studentBookNumber;

    public Student() {

    }
    public Student(Integer groupNumber, Double averageScore, Integer studentBookNumber) {
        this.groupNumber = groupNumber;
        this.averageScore = averageScore;
        this.studentBookNumber = studentBookNumber;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public Integer getStudentBookNumber() {
        return studentBookNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public void setStudentBookNumber(Integer studentBookNumber) {
        this.studentBookNumber = studentBookNumber;
    }

    @Override
    public String toString() {
        return "Студент {" +
                "Номер группы:" + groupNumber +
                ", Средний балл: " + averageScore +
                ", Номер зачетной книжки: " + studentBookNumber +
                " }";
    }

    @Override
    public boolean equals(Object object) {
        Student student = (Student) object;
        if(this.groupNumber.equals(student.getGroupNumber()) &&
                this.averageScore.equals(student.getAverageScore()) &&
                this.studentBookNumber.equals(student.getStudentBookNumber())) {
            return true;
        } else {
            return false;
        }
    }
}
