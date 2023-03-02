package sml;

import java.util.function.Supplier;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * An abstract factory for creating instructions.
 * Concrete factories should extend this class and implement the {@code createInstruction} method.
 */
public abstract class AbstractInstructionFactory {

    private static AbstractInstructionFactory instance;
    private static BeanFactory beanFactory;

    /**
     * Returns the singleton instance of the factory.
     * If the instance has not been initialized, creates it using Spring's BeanFactory and the "InstructionFactory" bean
     * defined in the "/beans.xml" configuration file.
     *
     * @return the singleton instance of the factory.
     */
    public static AbstractInstructionFactory getInstance() {
        if (instance == null) {
            BeanFactory beanFactory = new ClassPathXmlApplicationContext("/beans.xml");
            instance = (AbstractInstructionFactory) beanFactory.getBean("InstructionFactory");
        }
        return instance;
    }

    /**
     * Creates an instruction of the given opcode with the given label and arguments.
     *
     * @param label the label of the instruction.
     * @param opcode the opcode of the instruction.
     * @param scan a supplier of argument values.
     * @return the created instruction.
     * @throws IllegalArgumentException if the opcode is not recognized or the arguments are invalid.
     */
    public abstract Instruction createInstruction(String label, String opcode, Supplier<String> scan) throws IllegalArgumentException;
}



