package de.rieckpil.learning;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactoryMethods {

	public static void main(String[] args) {

		final List<String> strings = List.of("Hans", "Peter", "Bauer");
		final Map<Integer, String> mapping = Map.of(1, "Baum", 2, "Haus");
		final Map<Integer, String> mappingTwo = Map.ofEntries(Map.entry(1, "Baum"), Map.entry(2, "Haus"));
		final Set<String> sets = Set.of("Hans", "Meier", "Mueller");

		// sets.add("Tom"); throws exception -> umodifiable collection is created

        // Set.of("MAX", "MAX"); exception as duplicates are not checked here
	}

}
