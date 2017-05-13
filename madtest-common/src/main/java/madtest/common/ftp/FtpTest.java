package madtest.common.ftp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * connect to FTP over TLS Created by qct on 2015/6/16.
 */
public class FtpTest {

    static Logger logger = LoggerFactory.getLogger(FtpTest.class);

    public static void main(String[] args) {
        FTPSClient ftp = null;
        try {
            ftp = new FTPSClient(false);
            ftp.connect("10.22.205.104", 21);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                logger.debug(
                    "FtpUtils.createOrUpdateFile-----------------isPositiveCompletion----check---failed");
            }

            // Login
            if (ftp.login("123", "123")) {
                // Set protection buffer size
                ftp.execPBSZ(0);
                // Set data channel protection to private
                ftp.execPROT("P");
                // Enter local passive mode
                ftp.enterLocalPassiveMode();
                ftp.setBufferSize(1024 * 1024);
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftp.setKeepAlive(true);
            }

            logger.debug("working directory: " + ftp.printWorkingDirectory());

            InputStream is = new ByteArrayInputStream("hello123".getBytes());
            boolean store = ftp
                .storeFile("/working/admin/mayamrpass/mrPass_TaskDesc_new_qct.xml", is);

            is.close();
            logger.debug("store:" + store);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
