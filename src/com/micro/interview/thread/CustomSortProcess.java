package com.micro.interview.thread;

import com.micro.interview.models.People;
import java.util.Arrays;

public class CustomSortProcess extends Thread {
  private People[] internal;

  public CustomSortProcess(People[] internal) {
    super();
    this.internal = internal;
  }

  @Override
  public void run() {
    mergeSort(internal);
  }

  private void mergeSort(People[] internal) {
    if (internal.length > 1) {
      People[] leftArray = getLeftPart(internal);
      People[] rightArray = getRightPart(internal);

      mergeSort(leftArray);
      mergeSort(rightArray);

      mergeArray(internal, leftArray, rightArray);
    }
  }

  private void mergeArray(People[] internal, People[] leftArray, People[] rightArray) {
    int i1 = 0;
    int j1 = 0;

    for (int i = 0; i < internal.length; i++) {
      if (j1 >= rightArray.length || (i1 < leftArray.length && leftArray[i1].getBirthDate().compareTo(rightArray[j1].getBirthDate()) < 0)) {
        System.out.printf("Thread %d: %s %s %s %s\n", this.getId(), leftArray[i1].getId(), leftArray[i1].getMonth(), leftArray[i1].getDay(), leftArray[i1].getYear());
        internal[i] = leftArray[i1];
        i1++;
      } else {
        System.out.printf("Thread %d: %s %s %s %s\n", this.getId(), rightArray[j1].getId(), rightArray[j1].getMonth(), rightArray[j1].getDay(), rightArray[j1].getYear());
        internal[i] = rightArray[j1];
        j1++;
      }
    }
  }

  private People[] getRightPart(People[] internal) {
    int size = internal.length / 2;
    int remainingSize = internal.length - size;
    People[] right = new People[remainingSize];
    for (int i = 0; i < remainingSize; i++) {
      right[i] = internal[i + size];
    }
    return right;
  }

  private People[] getLeftPart(People[] internal) {
    int size = internal.length / 2;
    People[] left = new People[size];
    for (int i = 0; i < size; i++) {
      left[i] = internal[i];
    }
    return left;
  }

  public People[] getInternal() {
    return internal;
  }
}
