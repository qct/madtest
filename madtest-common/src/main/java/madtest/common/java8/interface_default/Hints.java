package madtest.common.java8.interface_default;

import java.lang.annotation.Repeatable;

/**
 * Created by qct on 2015/5/21.
 */
public @interface Hints {

    Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint {

    String value();
}
