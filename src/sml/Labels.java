package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The {@code Labels} class represents a mapping of labels to program addresses.
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label   the label
	 * @param address the address the label refers to
	 * @throws NullPointerException  if the label is {@code null}
	 * @throws IllegalArgumentException if the label is already present in the map
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		if (labels.containsKey(label)) {
			throw new IllegalArgumentException("Label '" + label + "' is duplicate");
		}
		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 * @throws NullPointerException if the label is not present in the map
	 */
	public int getAddress(String label) {
		if (!labels.containsKey(label)) {
			throw new NullPointerException("Label '" + label + "' does not exist");
		}
		return labels.get(label);
	}

	/**
	 * Returns the string representation of the labels map.
	 *
	 * @return a string in the form "[label -> address, label -> address, ..., label -> address]"
	 */
	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "[", "]");
		labels.entrySet().stream()
				.map(entry -> entry.getKey() + " -> " + entry.getValue())
				.forEach(joiner::add);
		return joiner.toString();
	}

	/**
	 * Compares this {@code Labels} instance with the specified object for equality.
	 *
	 * @param o the object to compare to
	 * @return {@code true} if the specified object is equal to this {@code Labels} instance, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Labels otherLabels)) return false;
		return labels.equals(otherLabels.labels);
	}

	/**
	 * Returns a hash code for this {@code Labels} instance.
	 *
	 * @return a hash code value for this {@code Labels} instance
	 */
	@Override
	public int hashCode() {
		return Objects.hash(labels);
	}

	/**
	 * Removes all labels from the map.
	 */
	public void reset() {
		labels.clear();
	}
}
