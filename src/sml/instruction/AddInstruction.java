package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * The AddInstruction class represents an instruction in a machine language program that adds the value of one register
 * to another register and stores the result in the first register. This instruction extends the Instruction class.
 */
public class AddInstruction extends Instruction {

	/** The name of the result register. */
	private final RegisterName result;

	/** The name of the source register. */
	private final RegisterName source;

	/** The opcode for the add instruction. */
	public static final String OP_CODE = "add";

	/**
	 * Constructs an AddInstruction object with a label, result register name, and source register name.
	 *
	 * @param label the label for the instruction, or null if none
	 * @param result the name of the result register
	 * @param source the name of the source register
	 */
	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Executes the add instruction on the specified Machine object.
	 *
	 * This method retrieves the values of the two registers specified in the constructor, adds them together, and stores
	 * the result in the result register. The method returns a constant value indicating that the program counter should be
	 * updated normally.
	 *
	 * @param m the Machine object on which to execute the instruction
	 * @return the value to add to the program counter after the instruction is executed
	 */
	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * Returns a string representation of the instruction in the format "label: opcode result source".
	 *
	 * @return a string representation of the instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}
}

