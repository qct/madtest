package madtest.common.guava.basic;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by qct on 2015/5/25.
 */
public class ObjectsTest {
    public static void main(String[] args) {
        System.out.println(java.util.Objects.equals(null, "a"));
        System.out.println(Objects.equal(null, "a"));
        System.out.println(MoreObjects.toStringHelper("MyObject").add("name", "qct"));
    }
}
