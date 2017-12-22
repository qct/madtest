package madtest.common.java8.function;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <p>Created by Damon.Q on 2017/3/10.
 */
public class Student {

    String firstName;

    String lastName;

    Double grade;

    Double feeDiscount = 0.0;

    Double baseFee = 10000.0;

    public Student(String firstName, String lastName, Double grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public String getFirstName() {
        return firstName;
    }

    public Double getGrade() {
        return grade;
    }

    public void printFee() {
        Double newFee = baseFee - ((baseFee * feeDiscount) / 100);
        System.out.println("The fee after discount: " + newFee);
    }

    public static void main(String[] args) {
        Student student1 = new Student("Ashok", "Kumar", 9.5);
        student1 = PredicateConsumerDemo.updateStudentFee(
            student1,
            student -> student.grade > 8.5,
            student -> student.feeDiscount = 30.0);
        student1.printFee();

        Student student2 = new Student("Ashok", "Kumar", 8.0);
        student2 = PredicateConsumerDemo.updateStudentFee(
            student2,
            student -> student.grade >= 8,
            student -> student.feeDiscount = 20.0);
        student2.printFee();

        Supplier<Student> st = () -> new Student("damon", "q", 10.0);
    }
}

class PredicateConsumerDemo {
    public static Student updateStudentFee(Student student, Predicate<Student> predicate, Consumer<Student> consumer) {
        if(predicate.test(student)) {
            consumer.accept(student);
        }
        return student;
    }
}