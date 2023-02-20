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

class SubInstructionTest {
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
        registers.set(EAX, 7);
        registers.set(EBX, 5);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        assertEquals(2, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new SubInstruction(null, EAX, EBX);
        instruction.execute(machine);
        assertEquals(-11, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidThree() {
        registers.set(Registers.Register.EAX, 5);
        registers.set(Registers.Register.EBX, 3);
        SubInstruction SubInstruction = new SubInstruction(null, EAX, EBX);
        SubInstruction.execute(machine);
        assertEquals(2, machine.getRegisters().get(EAX));
    }

    @Test
    void testToString() {
        SubInstruction SubInstruction = new SubInstruction(null, EAX, EBX);
        assertEquals("sub EAX EBX", SubInstruction.toString());
    }

    @Test
    void testEquals() {
        SubInstruction SubInstruction1 = new SubInstruction(null, EAX, EBX);
        SubInstruction SubInstruction2 = new SubInstruction(null, EAX, EBX);
        SubInstruction SubInstruction3 = new SubInstruction(null, EAX, EDI);
        AddInstruction AddInstruction4 = new AddInstruction(null, EAX, EBX);
        assertEquals(SubInstruction1, SubInstruction2);
        assertNotEquals(SubInstruction1, SubInstruction3);
        assertNotEquals(SubInstruction1, AddInstruction4);
    }

    @Test
    void testHashCode() {
        SubInstruction SubInstruction1 = new SubInstruction(null, EAX, EBX);
        SubInstruction SubInstruction2 = new SubInstruction(null, EAX, EBX);
        SubInstruction SubInstruction3 = new SubInstruction(null, EAX, EDI);
        assertEquals(SubInstruction1.hashCode(), SubInstruction2.hashCode());
        assertNotEquals(SubInstruction1.hashCode(), SubInstruction3.hashCode());
    }
}