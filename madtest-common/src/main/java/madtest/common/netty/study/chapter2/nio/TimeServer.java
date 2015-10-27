package madtest.common.netty.study.chapter2.nio;

/**
 * Created by quchentao on 15/10/25.
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //
            }
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);

        new Thread(timeServer, "NIO MultiplexerTimeServer-001").start();
    }
}
