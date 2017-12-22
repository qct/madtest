package madtest.common.bean;

/**
 * Created by alex on 2016/8/12.
 */
public class OuterClass {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static class InnerClass {

        private String sex;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
