package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
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
                    case "RegisterName" -> args[i] = Register.valueOf(scan());
                    case "int" -> args[i] = Integer.parseInt(scan());
                    case "String" -> args[i] = scan();

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


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}