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

class MulInstructionTest {
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
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        assertEquals(-30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidThree() {
        registers.set(Registers.Register.EAX, 5);
        registers.set(Registers.Register.EBX, 3);
        MulInstruction MulInstruction = new MulInstruction(null, EAX, EBX);
        MulInstruction.execute(machine);
        assertEquals(15, registers.get(Registers.Register.EAX));
    }

    @Test
    void testToString() {
        MulInstruction MulInstruction = new MulInstruction(null, EAX, EBX);
        assertEquals("mul EAX EBX", MulInstruction.toString());
    }

    @Test
    void testEquals() {
        MulInstruction MulInstruction1 = new MulInstruction(null, EAX, EBX);
        MulInstruction MulInstruction2 = new MulInstruction(null, EAX, EBX);
        MulInstruction MulInstruction3 = new MulInstruction(null, EAX, EDI);
        assertEquals(MulInstruction1, MulInstruction2);
        assertNotEquals(MulInstruction1, MulInstruction3);
    }

    @Test
    void testHashCode() {
        MulInstruction MulInstruction1 = new MulInstruction(null, EAX, EBX);
        MulInstruction MulInstruction2 = new MulInstruction(null, EAX, EBX);
        MulInstruction MulInstruction3 = new MulInstruction(null, EAX, EDI);
        assertEquals(MulInstruction1.hashCode(), MulInstruction2.hashCode());
        assertNotEquals(MulInstruction1.hashCode(), MulInstruction3.hashCode());
    }
}