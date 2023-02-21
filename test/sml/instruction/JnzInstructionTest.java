package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static sml.Registers.Register.*;

class JnzInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        Instruction dest_instruction = new JnzInstruction("x", EAX, "l");
        Instruction rem_instruction = new JnzInstruction("z", EAX, "x");
        List<Instruction> instructions = machine.getProgram();
        instructions.add(dest_instruction);
        instructions.add(rem_instruction);
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
        Instruction instruction = new JnzInstruction(null, EAX, "z");
        int programCounter = instruction.execute(machine);
        assertEquals(1, programCounter);
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 0);
        Instruction instruction = new JnzInstruction(null, EAX, "z");
        int programCounter = instruction.execute(machine);
        assertEquals(programCounter, Instruction.NORMAL_PROGRAM_COUNTER_UPDATE);
    }

    @Test
    void testToString() {
        JnzInstruction JnzInstruction = new JnzInstruction(null, EAX, "z");
        assertEquals("jnz EAX z", JnzInstruction.toString());
    }

    @Test
    void testEquals() {
        JnzInstruction JnzInstruction1 = new JnzInstruction(null, EAX, "z");
        JnzInstruction JnzInstruction2 = new JnzInstruction(null, EAX, "z");
        JnzInstruction JnzInstruction3 = new JnzInstruction(null, EAX, "x");
        assertEquals(JnzInstruction1, JnzInstruction2);
        assertNotEquals(JnzInstruction1, JnzInstruction3);
    }

    @Test
    void testHashCode() {
        JnzInstruction JnzInstruction1 = new JnzInstruction(null, EAX, "z");
        JnzInstruction JnzInstruction2 = new JnzInstruction(null, EAX, "z");
        JnzInstruction JnzInstruction3 = new JnzInstruction(null, EAX, "x");
        assertEquals(JnzInstruction1.hashCode(), JnzInstruction2.hashCode());
        assertNotEquals(JnzInstruction1.hashCode(), JnzInstruction3.hashCode());
    }
}