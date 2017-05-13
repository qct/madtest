package madtest.common.util;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Created by Damon.Q on 2017/4/22.
 */
public class RenameTest {

    private static final Logger logger = LoggerFactory.getLogger(RenameTest.class);

    public static void main(String[] args) {
        File parentDir = new File("D:\\自动收付款项目\\A项目管理\\03项目报告\\周报");
        File newDir = new File("D:\\自动收付款项目\\A项目管理\\03项目报告\\周报2");

        if (parentDir.isDirectory()) {
            File[] files = parentDir.listFiles();
            for (File file : files) {
                logger.debug("old file: {}", file.getName());
                String[] split = file.getName().replace(".xlsx", "").split("_");
                String newName = split[0] + "_" + split[2] + "_" + split[3] + "_" + split[1] + ".xlsx";
                logger.debug("new file: {}", newName);

                File newFile = new File(newDir, newName);

                if (newFile.exists()) {
                    logger.debug("file exists");
                    continue;
                }
                boolean success = file.renameTo(newFile);
                logger.debug("rename success? {}", success);
            }
        }
    }
}
