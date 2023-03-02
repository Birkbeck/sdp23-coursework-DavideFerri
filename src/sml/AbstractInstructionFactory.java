package sml;

import java.util.function.Supplier;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractInstructionFactory {

    private static AbstractInstructionFactory instance;
    private static BeanFactory beanFactory;

    public static AbstractInstructionFactory getInstance() {
        if (instance == null) {
            BeanFactory beanFactory = new ClassPathXmlApplicationContext("/beans.xml");
            instance = (AbstractInstructionFactory) beanFactory.getBean("InstructionFactory");
        }
        return instance;
    }

    public abstract Instruction createInstruction(String label, String opcode, Supplier<String> scan) throws IllegalArgumentException;


}
