package spi;

import javax.print.Doc;
import java.util.List;

/**
 * Created by qct on 2015/3/14.
 */
public class FileSearch implements Search {
    @Override
    public List<Doc> search(String keyword) {
        System.out.println("now use file system search. keyword:" + keyword);
        return null;
    }
}
