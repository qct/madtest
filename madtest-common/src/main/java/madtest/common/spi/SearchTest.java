package madtest.common.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by qct on 2015/3/14.
 */
public class SearchTest {

    public static void main(String[] args) {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> searches = s.iterator();
        while (searches.hasNext()) {
            Search curSearch = searches.next();
            curSearch.search("test");
        }
    }
}
