package madtest.common.guava.basic;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.List;
import javax.annotation.Nullable;

/**
 * Created by qct on 2015/5/25.
 */
public class OrderingTest {

    public static void main(String[] args) {
        Ordering<Foo> ordering = Ordering.natural().nullsFirst()
            .onResultOf(new Function<Foo, Comparable>() {
                @Override
                public Comparable apply(@Nullable Foo foo) {
                    return foo.sortedBy;
                }
            });

        List<Foo> fooList = Lists.newArrayList(new Foo(), new Foo());
        System.out.println(ordering.min(fooList));
    }
}

class Foo {

    @Nullable
    String sortedBy;
    int notSortedBy;
}
