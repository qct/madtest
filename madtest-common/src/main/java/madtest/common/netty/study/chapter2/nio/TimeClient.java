package madtest.common.netty.study.chapter2.nio;

/**
 * Created by quchentao on 15/10/26.
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //
            }
        }
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
//        for(int i=0;i<10;i++) {
//            new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-" + i).start();
//        }
    }
}
