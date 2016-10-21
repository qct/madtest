package madtest.common.load;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by alex on 2016/10/20.
 */
public class InitialClassTest {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        Class classForName = Class.forName("madtest.common.load.InitialClass", true,
                new URLClassLoader(new URL[]{new URL("file:C:\\IntelliJ\\madtest\\madtest-common\\target")},
                        InitialClassTest.class.getClassLoader()));

        Class classFromClassLoader = new URLClassLoader(new URL[]{new URL("file:C:\\IntelliJ\\madtest\\madtest-common\\target")},
                InitialClassTest.class.getClassLoader()).loadClass("madtest.common.load.InitialClass");
    }


}
