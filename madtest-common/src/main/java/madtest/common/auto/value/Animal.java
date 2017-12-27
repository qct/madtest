package madtest.common.auto.value;

import com.google.auto.value.AutoValue;

/**
 * default description.
 *
 * @author qct
 * @date 2017/12/26
 * @since 1.0
 */
@AutoValue
abstract class Animal {

    static Animal create(String name, int numberOfLegs) {
        return new AutoValue_Animal(name, numberOfLegs);
    }

    abstract String name();

    abstract int numberOfLegs();
}
