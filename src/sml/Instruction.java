package sml;

// TODO: write a JavaDoc for the class

import java.util.Objects;

/**
 * The Instruction class represents an abstract instruction in a machine language program.
 *
 * This class provides functionality for retrieving the label and opcode of an instruction, as well as a method for
 * executing an instruction on a Machine object. Subclasses of this class should implement the execute, toString,
 * equals, and hashCode methods to provide their own behavior.
 */
public abstract class Instruction {

	/** The optional label for the instruction. */
	protected final String label;

	/** The opcode for the instruction. */
	protected final String opcode;

	/** A constant value indicating how to update the program counter. */
	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Constructs an Instruction object with a label and opcode.
	 *
	 * @param label the optional label for the instruction, or null if none
	 * @param opcode the opcode for the instruction
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	/**
	 * Returns the label for the instruction.
	 *
	 * @return the label for the instruction, or null if none
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the opcode for the instruction.
	 *
	 * @return the opcode for the instruction
	 */
	public String getOpcode() {
		return opcode;
	}

	/**
	 * Executes the instruction on the specified Machine object.
	 *
	 * This method should be implemented by subclasses of Instruction to provide their own behavior. The method returns
	 * an integer value
	 *
	 * @param machine the Machine object on which to execute the instruction
	 * @return an integer
	 */
	public abstract int execute(Machine machine);


	/**
	 * Returns the label string for this instruction, or the empty string if no label is present.
	 *
	 * This method is used to construct the string representation of the instruction. If a label is present, it is
	 * included in the string with a trailing colon; otherwise, the string is empty.
	 *
	 * @return the label string for this instruction, or the empty string if no label is present
	 */
	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	/**
	 * Returns a string representation of the instruction.
	 *
	 * This method should be implemented by subclasses of Instruction to provide their own behavior. The string should
	 * be formatted as "label: opcode args" if a label is present, or "opcode args" otherwise.
	 *
	 * @return a string representation of the instruction
	 */
	@Override
	public abstract String toString();

	/**
	 * Compares this Instruction to the specified object for equality.
	 *
	 * This method should be implemented by subclasses of Instruction to provide their own behavior.
	 *
	 * @param o the object to compare to this Instruction
	 * @return true if the specified object is equal to this Instruction, false otherwise
	 */
	@Override
	public abstract boolean equals(Object o);

	/**
	 * Returns a hash code value for this Instruction.
	 *
	 * This method should be implemented by subclasses of Instruction to provide their own behavior.
	 *
	 * @return a hash code value for this Instruction
	 */
	@Override
	public abstract int hashCode();
}

