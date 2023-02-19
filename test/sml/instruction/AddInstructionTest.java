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

class AddInstructionTest {
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
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void testExecute() {
    Registers registers = new Registers();
    Machine machine = new Machine(registers);
    registers.set(Registers.Register.EAX, 5);
    registers.set(Registers.Register.EBX, 3);
    AddInstruction addInstruction = new AddInstruction(null, EAX, EBX);
    addInstruction.execute(machine);
    assertEquals(8, registers.get(Registers.Register.EAX));
  }

  @Test
  void testToString() {
    AddInstruction addInstruction = new AddInstruction(null, EAX, EBX);
    assertEquals("add EAX EBX", addInstruction.toString());
  }

  @Test
  void testEquals() {
    AddInstruction addInstruction1 = new AddInstruction(null, EAX, EBX);
    AddInstruction addInstruction2 = new AddInstruction(null, EAX, EBX);
    AddInstruction addInstruction3 = new AddInstruction(null, EAX, EDI);
    assertEquals(addInstruction1, addInstruction2);
    assertNotEquals(addInstruction1, addInstruction3);
  }

  @Test
  void testHashCode() {
    AddInstruction addInstruction1 = new AddInstruction(null, EAX, EBX);
    AddInstruction addInstruction2 = new AddInstruction(null, EAX, EBX);
    AddInstruction addInstruction3 = new AddInstruction(null, EAX, EDI);
    assertEquals(addInstruction1.hashCode(), addInstruction2.hashCode());
    assertNotEquals(addInstruction1.hashCode(), addInstruction3.hashCode());
  }
}
