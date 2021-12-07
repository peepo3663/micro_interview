package com.micro.interview.models;

import java.util.Comparator;

public class SortByDateComparator implements Comparator<People> {
  @Override
  public int compare(People o1, People o2) {
    return o1.getBirthDate().compareTo(o2.getBirthDate());
  }
}
