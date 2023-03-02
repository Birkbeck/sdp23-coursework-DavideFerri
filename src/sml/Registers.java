package sml;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class representing the registers used in the program. The registers are identified by their names,
 * which are defined as elements of the Register enumeration. Each register holds an integer value.
 */
public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();

    /**
     * Enumeration of the available registers.
     */
    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI;
    }

    /**
     * Constructs a new instance and initializes all registers to zero.
     */
    public Registers() {
        clear(); // the class is final
    }

    /**
     * Clears all registers, setting their values to zero.
     */
    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register)register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register)register);
    }

    /**
     * Compares this {@code Registers} instance to the specified object for equality. Returns {@code true} if the
     * specified object is also an instance of {@code Registers} and its register values are equal to this instance's
     * register values.
     *
     * @param o the object to compare to
     * @return {@code true} if the specified object is equal to this instance, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers other) {
            return registers.equals(other.registers);
        }
        return false;
    }

    /**
     * Returns a hash code value for this {@code Registers} instance. The hash code is based on the hash code of the
     * register map.
     *
     * @return a hash code value for this {@code Registers} instance
     */
    @Override
    public int hashCode() {
        return registers.hashCode();
    }

    /**
     * Returns a string representation of this {@code Registers} instance. The string representation consists of a list
     * of the register names and their values, sorted by register name.
     *
     * @return a string representation of this {@code Registers} instance
     */
    @Override
    public String toString() {
        return registers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]")) ;
    }
}
