package sml.instruction;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.RegisterName;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The JnzInstruction class represents an instruction in a machine language program that reads the content of a register and
 * if it's different than zero it will change the program counter to execute instruction with label next
 */
public class JnzInstruction extends Instruction {

	/** The name of the result register. */
	private final RegisterName register;

	/** The name of the source register. */
	private final String other_label;

	/** The opcode for the jnz instruction. */
	public static final String OP_CODE = "jnz";

	/**
	 * Constructs an JnzInstruction object with a label, register name, and the label of the other instruction.
	 *
	 * @param label the label for the instruction, or null if none
	 * @param register the name of the result register
	 * @param other_label the label of the other instruction
	 */
	public JnzInstruction(String label, RegisterName register, String other_label) {
		super(label, OP_CODE);
		this.register = register;
		this.other_label = other_label;
	}

	/**
	 * Executes the jnz instruction on the specified Machine object.
	 *
	 * This method retrieves the values of the register specified and if it's different
	 * than zero it will return the value of the program counter to process  the instruction with the other label next
	 *
	 * @param m the Machine object on which to execute the instruction
	 * @return the new program counter or the normal program counter update if condition does not hold
	 */
	@Override
	public int execute(Machine m) {
		int value = m.getRegisters().get(register);
		if (value == 0) return NORMAL_PROGRAM_COUNTER_UPDATE;
		else{
			Labels labels = m.getLabels();
			return labels.getAddress(this.other_label);
		}
	}

	/**
	 * Returns a string representation of the instruction in the format "label: opcode register other_label".
	 *
	 * @return a string representation of the instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + register + " " + other_label;
	}

	/**
	 * Compares this JnzInstruction to the specified object for equality.

	 * @param o the object to compare to this Instruction
	 * @return true if the specified object is equal to this Instruction, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof JnzInstruction that)) return false;
		return Objects.equals(register, that.register) && Objects.equals(other_label, that.other_label)
				&& Objects.equals(that.label, label);
	}

	/**
	 * Returns a hash code value for this JnzInstruction.

	 * @return a hash code value for this Instruction
	 */
	@Override
	public int hashCode() {
		return Objects.hash(register, other_label);
	}
}

