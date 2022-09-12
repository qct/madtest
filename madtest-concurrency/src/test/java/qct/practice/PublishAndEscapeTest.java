package qct.practice;

import org.junit.jupiter.api.Test;

/**
 * <p>Created by qdd on 2022/6/5.
 */
public class PublishAndEscapeTest {

    @Test
    void name() {
        EventSource source = new EventSource();
        ThisEscape thisEscape = new ThisEscape(source);
    }

    static class ThisEscape {

        private final int val = 10;

        public ThisEscape(EventSource source) {
            source.registerListener(event -> {
                // doSomething(e);
                // in this internal class, 包含了对ThisEscape实例的隐含引用。
                System.out.println("we can access `val` field from ThisEscape instance: " + val);
                System.out.println("This called escape while we publish EventListener");
            });
        }
    }

    static class EventSource {

        public void registerListener(EventListener eventListener) {
            System.out.println("Register and fire an Event...");
            eventListener.onEvent(new Event());
        }
    }

    static class Event {}

    interface EventListener {

        void onEvent(Event event);
    }
}
