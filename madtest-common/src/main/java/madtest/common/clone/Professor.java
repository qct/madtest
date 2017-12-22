package madtest.common.clone;

/**
 * Created by alex on 2016/10/18.
 */
public class Professor implements Cloneable {

    String name;
    int age;

    Professor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();//Object 中的clone()识别出你要复制的是哪一个对象。
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        return o;
    }
}
