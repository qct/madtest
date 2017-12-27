package madtest.common.auto.value.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * default description.
 *
 * @author qct
 * @date 2017/12/26
 * @since 1.0
 */
public class AnimalTest {

    @Test
    public void testAnimal() {
        Animal dog = Animal.builder().setName("dog").setNumberOfLegs(4).build();
        assertEquals("dog", dog.name());
        assertEquals(4, dog.numberOfLegs());

        // You probably don't need to write assertions like these; just illustrating.
        assertTrue(Animal.builder().setName("dog").setNumberOfLegs(4).build().equals(dog));
        assertFalse(Animal.builder().setName("cat").setNumberOfLegs(4).build().equals(dog));
        assertFalse(Animal.builder().setName("dog").setNumberOfLegs(2).build().equals(dog));

        assertEquals("Animal{name=dog, numberOfLegs=4}", dog.toString());
    }
}