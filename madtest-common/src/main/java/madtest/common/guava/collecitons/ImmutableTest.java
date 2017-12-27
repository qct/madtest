package madtest.common.guava.collecitons;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.UnmodifiableIterator;

/**
 * Created by qct on 2015/6/4.
 */
public class ImmutableTest {

    public static void main(String[] args) {
        ImmutableSet<Integer> set = ImmutableSet.of(1, 2);
        ImmutableSortedSet<String> set2 = ImmutableSortedSet.of("b", "a", "d", "c");
        for (String aSet2 : set2) {
            System.out.println(aSet2);
        }

        ImmutableSet<String> foobar = ImmutableSet.of("foo", "bar", "baz");
        ImmutableList<String> defensiveCopy = ImmutableList.copyOf(foobar);

        HashMultiset<String> hashMultiset = HashMultiset.create();
        hashMultiset.add("b");
        hashMultiset.add("b");
        hashMultiset.add("b");
        hashMultiset.add("a");
        hashMultiset.add("c");
        hashMultiset.add("c");

        System.out.println(hashMultiset.count("b"));

    }
}
