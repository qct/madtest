package madtest.common.pattern.proxy;

/**
 * Created by alex on 2016/8/26.
 */
public class Client {
    public static void main(String[] args) {
        //static
        Greeting greetingProxy = new GreetingProxy(new GreetingImpl());
        greetingProxy.sayHello("Jack");

        //jdk dynamic
        Greeting greeting = new JDKDynamicProxy(new GreetingImpl()).getProxy();
        greeting.sayHello("Jack");

        //cglib
        Greeting greeting2 = CGLibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
        greeting2.sayHello("Jack");

    }
}
