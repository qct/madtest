package madtest.common.spi;

import javax.print.Doc;
import java.util.List;

/**
 * Created by qct on 2015/3/14.
 */
public interface Search {

    List<Doc> search(String keyword);
}
