package madtest.common.bean;

/**
 * Created by alex on 2016/8/12.
 */
public class TargetClass {

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "TargetClass{" +
            "sex='" + sex + '\'' +
            '}';
    }
}
