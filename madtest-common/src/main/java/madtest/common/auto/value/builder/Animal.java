package madtest.common.auto.value.builder;

import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;

/**
 * default description.
 *
 * @author qct
 * @date 2017/12/26
 * @since 1.0
 */
@AutoValue
abstract class Animal {

    @Nullable
    abstract String name();

    abstract int numberOfLegs();

    static Builder builder() {
        return new AutoValue_Animal.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {

        abstract Builder setName(String value);

        abstract Builder setNumberOfLegs(int value);

        abstract Animal build();
    }
}
