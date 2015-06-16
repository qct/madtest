package ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by qct on 2015/6/16.
 */
public class FtpTest {

    static Logger logger = LoggerFactory.getLogger(FtpTest.class);

    public static void main(String[] args) {
        FTPClient ftp = new FTPSClient(false);
        try {
            ftp.setControlEncoding("UTF-8");
            ftp.connect("10.22.205.104", 21);
            Boolean isSuccess = ftp.login("123123123", "123123123");
            logger.debug("FtpUtils.createOrUpdateFile-----------------login---" + isSuccess + "---user:" + ftp.getRemoteAddress() + " password:");
            ftp.setBufferSize(1024);
            ftp.enterLocalPassiveMode();
            //ftp.setControlEncoding("UTF-8");
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                logger.debug("FtpUtils.createOrUpdateFile-----------------isPositiveCompletion----check---failed");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
