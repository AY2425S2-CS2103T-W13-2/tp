package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code Description}.
 */
public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription1 = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription1));

        String invalidDescription2 = "  ";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription2));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid description
        assertTrue(Description.isValidDescription(" -  ")); // one symbol only
        assertTrue(Description.isValidDescription("I")); // one character only
        assertTrue(Description.isValidDescription("I am a description")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("I am 12345")); // alphanumeric characters
        assertTrue(Description.isValidDescription("I am a description with spaces")); // with spaces
        assertTrue(Description.isValidDescription("I am a description with special characters "
                + "like !@#$%^&*()_+")); // with special characters
        assertTrue(Description.isValidDescription("I am a description with special characters like "
                + "!@#$%^&*()_+ and numbers 12345")); // with special characters and numbers
    }

    @Test
    public void testToString() {
        Description description = new Description("I am a description");
        assertEquals("I am a description", description.toString());
    }

    @Test
    public void testEquals() {
        Description description = new Description("I am a description");
        Description descriptionCopy = new Description("I am a description");
        Description descriptionDifferent = new Description("I am a different description");

        // same object -> returns true
        assertTrue(description.equals(description));

        // same values -> returns true
        assertTrue(description.equals(descriptionCopy));

        // different types -> returns false
        assertFalse(description.equals(1));

        // null -> returns false
        assertFalse(description.equals(null));

        // different description -> returns false
        assertFalse(description.equals(descriptionDifferent));
    }
}
