package madtest.common.spi;

import java.util.List;
import javax.print.Doc;

/**
 * Created by qct on 2015/3/14.
 */
public class DatabaseSearch implements Search {

    @Override
    public List<Doc> search(String keyword) {
        System.out.println("now use database search. keyword:" + keyword);
        return null;
    }
}
