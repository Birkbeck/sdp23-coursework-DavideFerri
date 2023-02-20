package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static sml.Registers.Register.*;

class OutInstructionTest {
    private Machine machine;
    private Registers registers;
    private final ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
    private final PrintStream oldConsole = System.out;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        System.setOut(new PrintStream(newConsole));
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        System.setOut(oldConsole);
        newConsole.reset();
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        assertEquals("5\n", newConsole.toString());
    }

    @Test
    void testToString() {
        OutInstruction OutInstruction = new OutInstruction(null, EAX);
        assertEquals("out EAX", OutInstruction.toString());
    }

    @Test
    void testEquals() {
        OutInstruction OutInstruction1 = new OutInstruction(null, EBX);
        OutInstruction OutInstruction2 = new OutInstruction(null, EBX);
        OutInstruction OutInstruction3 = new OutInstruction(null, EDI);
        assertEquals(OutInstruction1, OutInstruction2);
        assertNotEquals(OutInstruction1, OutInstruction3);
    }

    @Test
    void testHashCode() {
        OutInstruction OutInstruction1 = new OutInstruction(null, EBX);
        OutInstruction OutInstruction2 = new OutInstruction(null, EBX);
        OutInstruction OutInstruction3 = new OutInstruction(null, EDI);
        assertEquals(OutInstruction1.hashCode(), OutInstruction2.hashCode());
        assertNotEquals(OutInstruction1.hashCode(), OutInstruction3.hashCode());
    }
}
