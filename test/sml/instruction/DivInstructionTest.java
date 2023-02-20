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

class DivInstructionTest {
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
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        assertEquals(0, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        assertEquals(0, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidThree() {
        registers.set(Registers.Register.EAX, 5);
        registers.set(Registers.Register.EBX, 3);
        DivInstruction DivInstruction = new DivInstruction(null, EAX, EBX);
        DivInstruction.execute(machine);
        assertEquals(1, registers.get(Registers.Register.EAX));
    }

    @Test
    void testToString() {
        DivInstruction DivInstruction = new DivInstruction(null, EAX, EBX);
        assertEquals("div EAX EBX", DivInstruction.toString());
    }

    @Test
    void testEquals() {
        DivInstruction DivInstruction1 = new DivInstruction(null, EAX, EBX);
        DivInstruction DivInstruction2 = new DivInstruction(null, EAX, EBX);
        DivInstruction DivInstruction3 = new DivInstruction(null, EAX, EDI);
        assertEquals(DivInstruction1, DivInstruction2);
        assertNotEquals(DivInstruction1, DivInstruction3);
    }

    @Test
    void testHashCode() {
        DivInstruction DivInstruction1 = new DivInstruction(null, EAX, EBX);
        DivInstruction DivInstruction2 = new DivInstruction(null, EAX, EBX);
        DivInstruction DivInstruction3 = new DivInstruction(null, EAX, EDI);
        assertEquals(DivInstruction1.hashCode(), DivInstruction2.hashCode());
        assertNotEquals(DivInstruction1.hashCode(), DivInstruction3.hashCode());
    }
}