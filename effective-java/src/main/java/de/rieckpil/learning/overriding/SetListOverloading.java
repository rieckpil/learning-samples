package de.rieckpil.learning.overriding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetListOverloading {

  public static void main(String[] args) {

    List<Integer> myIntList = new ArrayList<>();
    Set<Integer> myIntSet = new TreeSet<>();

    for (int i = -3; i < 3; i++) {
      myIntList.add(i);
      myIntSet.add(i);
    }

    for (int i = 0; i < 3; i++) {
      myIntList.remove(i);
      myIntSet.remove(i);
    }

    System.out.println("List: " + myIntList);
    System.out.println("Set: " + myIntSet);

  }

}
