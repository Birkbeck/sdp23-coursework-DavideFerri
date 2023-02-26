package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelsTest {
    private Labels labels;

    @BeforeEach
    void setUp() {
        labels = new Labels();
    }

    @AfterEach
    void tearDown() {
        labels = null;
    }

    @Test
    void addLabel() {
        labels.addLabel("M", 3);
        assertEquals("[M -> 3]", labels.toString());
        labels.addLabel("J", 4);
        assertThrows(IllegalArgumentException.class, ()->labels.addLabel("J", 4));
        // add duplicate
        assertThrows(IllegalArgumentException.class, ()->labels.addLabel("M", 4));
    }

    @Test
    void getAddress() {
        labels.addLabel("M", 0);
        labels.addLabel("J", 1);
        assertEquals(0, labels.getAddress("M"));
        assertEquals(1, labels.getAddress("J"));
        // non existent
        assertThrows(NullPointerException.class, ()->labels.getAddress("K"));
    }

    @Test
    void testToString() {
        labels.addLabel("M", 1);
        labels.addLabel("J", 0);
        assertEquals("[J -> 0, M -> 1]", labels.toString());
    }

    @Test
    void testEquals() {
        labels.addLabel("M", 0);
        labels.addLabel("J", 1);
        Labels new_labels = new Labels();
        new_labels.addLabel("M", 0);
        new_labels.addLabel("J", 1);
        assertEquals(labels, new_labels);
        assertNotEquals(labels, new Labels());
    }

    @Test
    void testHashCode() {
        labels.addLabel("M", 0);
        labels.addLabel("J", 1);
        Labels new_labels = new Labels();
        new_labels.addLabel("M", 0);
        new_labels.addLabel("J", 1);
        assertEquals(labels.hashCode(), new_labels.hashCode());
        assertNotEquals(labels.hashCode(), new Labels().hashCode());
    }

    @Test
    void reset() {
        labels.addLabel("M", 0);
        labels.addLabel("J", 1);
        labels.reset();
        assertEquals("[]", labels.toString());
    }
}