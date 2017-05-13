package madtest.common.clone;

/**
 * Created by alex on 2016/10/18.
 */
public class Student implements Cloneable {

    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 18);
        Student s2 = (Student) s1.clone();
        s2.name = "lisi";
        s2.age = 20;
        System.out.println("name=" + s1.name + "," + "age=" + s1.age);//修改学生2后，不影响学生1的值。
    }

    public Object clone() {
        Object o = null;
        try {
            o = (Student) super.clone();//Object 中的clone()识别出你要复制的是哪一个对象。
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return o;
    }
}
