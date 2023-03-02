package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * The MovInstruction class represents an instruction in a machine language program that replaces the value of one register
 * with one integer. This instruction extends the Instruction class.
 */
public class MovInstruction extends Instruction {

	/** The name of the register. */
	private final RegisterName register;

	/** The integer. */
	private final int value;

	/** The opcode for the instruction. */
	public static final String OP_CODE = "mov";

	/**
	 * Constructs a MovInstruction object with a label, register name, and integer.
	 *
	 * @param label the label for the instruction, or null if none
	 * @param register the name of the register
	 * @param value the integer
	 */
	public MovInstruction(String label, RegisterName register, int value) {
		super(label, OP_CODE);
		this.register = register;
		this.value = value;
	}

	/**
	 * Executes the mov instruction on the specified Machine object.
	 *
	 * This method replaces the value of one register with value
	 * The method returns a constant value indicating that the program counter should be
	 * updated normally.
	 *
	 * @param m the Machine object on which to execute the instruction
	 * @return the value to add to the program counter after the instruction is executed
	 */
	@Override
	public int execute(Machine m) {
		m.getRegisters().set(register, value);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * Returns a string representation of the instruction in the format "label: opcode register value".
	 *
	 * @return a string representation of the instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + register + " " + value;
	}

	/**
	 * Compares this MovInstruction to the specified object for equality.

	 * @param o the object to compare to this Instruction
	 * @return true if the specified object is equal to this Instruction, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MovInstruction that)) return false;
		return Objects.equals(register, that.register) &&
				Objects.equals(that.value, value)
				&& Objects.equals(that.label, label);
	}

	/**
	 * Returns a hash code value for this MovInstruction.

	 * @return a hash code value for this Instruction
	 */
	@Override
	public int hashCode() {
		return Objects.hash(register, value);
	}
}

