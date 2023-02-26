package sml.translator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Registers;
import sml.Translator;
import sml.instruction.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static sml.Registers.Register.*;

class TranslatorTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        registers.set(EAX, 0);
        registers.set(EDI, 0);
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void readAndTranslate() throws IOException {
        Translator translator = new Translator("test/resources/program.txt");
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        assertEquals(machine.getProgram().get(0), new AddInstruction(null, EAX, EDI));
        assertEquals(machine.getProgram().get(1), new DivInstruction(null, EAX, EDI));
        assertEquals(machine.getProgram().get(2), new MovInstruction("example_mov", EDI, 3));
        assertEquals(machine.getProgram().get(3), new OutInstruction("example_out", EAX));
        assertEquals(machine.getProgram().get(4), new SubInstruction(null, EAX, EDI));
        assertEquals(machine.getProgram().get(5), new MulInstruction("example_mul", EAX, EDI));
        assertEquals(machine.getProgram().get(6), new JnzInstruction("example_jnz", EDI, "example_mov"));
    }


}