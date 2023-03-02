package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

/**
 * The InstructionFactory class is responsible for creating Instruction objects
 * based on the opcode provided to the createInstruction method.
 *
 * This class implements the Singleton design pattern to ensure that there is only
 * one instance of the class in the application at runtime.
 */
public class InstructionFactory extends AbstractInstructionFactory{
    private static InstructionFactory instance;

    private InstructionFactory() {}

    /**
     * Returns the singleton instance of the InstructionFactory class.
     *
     * @return the singleton instance of the InstructionFactory class.
     */
    public static InstructionFactory getInstance() {
        if (instance == null) {
            instance = new InstructionFactory();
        }
        return instance;
    }

    /**
     * Creates an Instruction object based on the opcode provided.
     *
     * @param label the label of the instruction.
     * @param opcode the opcode of the instruction.
     * @param scan a Supplier function that can be used to obtain the argument values for the instruction.
     * @return the Instruction object created based on the opcode provided.
     * @throws IllegalArgumentException if the opcode provided is invalid or if the arguments provided for the instruction are invalid.
     */
    public Instruction createInstruction(String label, String opcode, Supplier<String> scan) throws IllegalArgumentException {

        String instructionClassName = "sml.instruction." + Character.toUpperCase(opcode.charAt(0)) + opcode.substring(1) + "Instruction";

        try {
            Class<?> instructionClass = Class.forName(instructionClassName);
            Constructor<?>[] constructors = instructionClass.getConstructors();
            Constructor<?> constructor = null;
            for (Constructor<?> c : constructors) {
                Class<?>[] parameterTypes = c.getParameterTypes();
                if (parameterTypes.length == 0 || !parameterTypes[0].equals(String.class)) {
                    continue;
                }
                constructor = c;
                break;
            }

            if (constructor == null) {
                throw new NoSuchMethodException("No suitable constructor found for instruction class: " + instructionClassName);
            }

            Object[] args = new Object[constructor.getParameterCount()];
            args[0] = label;
            for (int i = 1; i < args.length; i++) {
                Class<?> parameterType = constructor.getParameterTypes()[i];
                switch (parameterType.getSimpleName()) {
                    case "RegisterName" -> args[i] = Registers.Register.valueOf(scan.get());
                    case "int" -> args[i] = Integer.parseInt(scan.get());
                    case "String" -> args[i] = scan.get();

                    // Add cases for other argument types as needed
                    default -> throw new IllegalArgumentException("Unknown argument type: " + parameterType.getSimpleName());
                }
            }

            return (Instruction) constructor.newInstance(args);
        } catch (ClassNotFoundException e) {
            System.out.println("Unknown instruction with opcode: " + opcode);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
