package madtest.common.bean;

import org.springframework.beans.BeanUtils;

/**
 * Created by alex on 2016/8/12.
 */
public class TestMain {

    public static void main(String[] args) {
        OuterClass.InnerClass innerClass = new OuterClass.InnerClass();
        innerClass.setSex("male");

        TargetClass targetClass = new TargetClass();
        BeanUtils.copyProperties(innerClass, targetClass);
        System.out.println(targetClass);
    }
}
