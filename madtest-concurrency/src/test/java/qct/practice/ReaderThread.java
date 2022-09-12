package qct.practice;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * <p>Created by qdd on 2022/6/11.
 */
public class ReaderThread extends Thread {

    private static final int BUFSZ = 256;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // allow thread exit
        }
    }

    private void processBuffer(byte[] buf, int count) {
        System.out.println("processing");
    }
}
