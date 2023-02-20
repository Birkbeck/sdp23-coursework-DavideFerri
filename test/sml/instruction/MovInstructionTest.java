package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static sml.Registers.Register.*;

class MovInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EBX, 6);
        Instruction instruction = new MovInstruction(null, EBX, 7);
        instruction.execute(machine);
        assertEquals(7, machine.getRegisters().get(EBX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        Instruction instruction = new MovInstruction(null, EAX, 5);
        instruction.execute(machine);
        assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidThree() {
        registers.set(Registers.Register.EAX, 5);
        MovInstruction MovInstruction = new MovInstruction(null, EAX, 0);
        MovInstruction.execute(machine);
        assertEquals(0, registers.get(Registers.Register.EAX));
    }

    @Test
    void testToString() {
        MovInstruction MovInstruction = new MovInstruction(null, EAX, 25);
        assertEquals("mov EAX 25", MovInstruction.toString());
    }

    @Test
    void testEquals() {
        MovInstruction MovInstruction1 = new MovInstruction(null, EAX, 35);
        MovInstruction MovInstruction2 = new MovInstruction(null, EAX, 35);
        MovInstruction MovInstruction3 = new MovInstruction(null, EAX, 42);
        assertEquals(MovInstruction1, MovInstruction2);
        assertNotEquals(MovInstruction1, MovInstruction3);
    }

    @Test
    void testHashCode() {
        MovInstruction MovInstruction1 = new MovInstruction(null, EAX, 24);
        MovInstruction MovInstruction2 = new MovInstruction(null, EAX, 24);
        MovInstruction MovInstruction3 = new MovInstruction(null, EAX, 12);
        assertEquals(MovInstruction1.hashCode(), MovInstruction2.hashCode());
        assertNotEquals(MovInstruction1.hashCode(), MovInstruction3.hashCode());
    }
}
