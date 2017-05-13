package madtest.common.spi;

import java.util.List;
import javax.print.Doc;

/**
 * Created by qct on 2015/3/14.
 */
public interface Search {

    List<Doc> search(String keyword);
}
