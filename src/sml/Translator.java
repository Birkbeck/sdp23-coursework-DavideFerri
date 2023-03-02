package sml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for translating a SML program into a list of instructions.
 */
public final class Translator {

    private final String fileName; // source file of SML code
    private final AbstractInstructionFactory instructionFactory; // instruction factory instance
    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    /**
     * Constructs a new `Translator` object that will read and translate the specified file.
     *
     * @param fileName the name of the file containing the SML program
     */
    public Translator(String fileName) {
        this.fileName = fileName;
        instructionFactory = AbstractInstructionFactory.getInstance();
    }

    /**
     * Translates the SML program in the file into a list of instructions.
     *
     * @param labels  the `Labels` object to store label information in
     * @param program the list of `Instruction` objects to store the translated program in
     * @throws IOException if an I/O error occurs while reading the input file
     */
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
     * Translates the current line into an instruction with the given label.
     *
     * @param label the instruction label
     * @return the new `Instruction` object, or `null` if the line is empty
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        return instructionFactory.createInstruction(label, opcode, this::scan);
    }

    /**
     * Extracts the label from the current line, if there is one.
     *
     * @return the label, or `null` if there is no label on the current line
     */
    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /**
     * Extracts the next word from the current line and removes it from the line.
     *
     * @return the next word, or an empty string if there are no more words on the line
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