package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * The OutInstruction class represents an instruction in a machine language program that prints the value of one register
 * on console. This instruction extends the Instruction class.
 */
public class OutInstruction extends Instruction {

	/** The name of the register. */
	private final RegisterName register;

	/** The opcode for the add instruction. */
	public static final String OP_CODE = "out";

	/**
	 * Constructs a OutInstruction object with a label, and register name.
	 *
	 * @param label the label for the instruction, or null if none
	 * @param register the name of the register
	 */
	public OutInstruction(String label, RegisterName register) {
		super(label, OP_CODE);
		this.register = register;
	}

	/**
	 * Executes the out instruction on the specified Machine object.
	 *
	 * This method retrieves the values of one register and prints it on console
	 * The method returns a constant value indicating that the program counter should be
	 * updated normally.
	 *
	 * @param m the Machine object on which to execute the instruction
	 * @return the value to add to the program counter after the instruction is executed
	 */
	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(register);
		System.out.println(value1);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * Returns a string representation of the instruction in the format "label: opcode register".
	 *
	 * @return a string representation of the instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + register;
	}

	/**
	 * Compares this OutInstruction to the specified object for equality.

	 * @param o the object to compare to this Instruction
	 * @return true if the specified object is equal to this Instruction, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OutInstruction that)) return false;
		return Objects.equals(register, that.register)
				&& Objects.equals(that.label, label);
	}

	/**
	 * Returns a hash code value for this OutInstruction.

	 * @return a hash code value for this Instruction
	 */
	@Override
	public int hashCode() {
		return Objects.hash(register);
	}
}

