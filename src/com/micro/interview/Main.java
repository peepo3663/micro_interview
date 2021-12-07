package com.micro.interview;

import com.micro.interview.models.People;
import com.micro.interview.thread.CustomSortProcess;
import com.micro.interview.thread.DefaultSortProcess;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

  private final int numberOfThreads = 2;
  private List<People> dataSource;

  public static void main(String[] args) {
    // start programs
    Main m = new Main();
    // read file
    m.readTheInputFileAndMapToModels();
    // sort and printing
    m.sortingWithJavaReadyMade();
    // read file
    m.readTheInputFileAndMapToModels();
    m.sortWithCustomAlgo();
  }

  public void readTheInputFileAndMapToModels() {
    if (dataSource == null) {
      this.dataSource = new ArrayList<>();
    }
    this.dataSource.clear();
    try {
      FileInputStream fileInputStream = new FileInputStream("input.txt");
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        People p = new People(line);
        this.dataSource.add(p);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void sortingWithJavaReadyMade() {
    List<People> peopleFirstHalf = this.dataSource.subList(0, this.dataSource.size() / 2);
    List<People> peopleSecondHalf = this.dataSource.subList(this.dataSource.size() / 2, this.dataSource.size());
    DefaultSortProcess firstH = new DefaultSortProcess(peopleFirstHalf);
    DefaultSortProcess secondH = new DefaultSortProcess(peopleSecondHalf);
    Thread thread1 = new Thread(firstH);
    Thread thread2 = new Thread(secondH);

    thread1.start();
    thread2.start();
    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    thread1.interrupt();
    thread2.interrupt();

    People [] sortedLeft = firstH.getPeopleList(new People[0]);
    People [] sortedRight = secondH.getPeopleList().toArray(new People[0]);
    People [] merged = new People[sortedLeft.length + sortedRight.length];
    mergedResult(sortedLeft, sortedRight, merged);
    printDataByThread(merged);
  }

  public void sortWithCustomAlgo() {
    // merge sort
    // RUN TIME O(n log n)
    List<People> peopleFirstHalf = this.dataSource.subList(0, this.dataSource.size() / 2);
    List<People> peopleSecondHalf = this.dataSource.subList(this.dataSource.size() / 2, this.dataSource.size());

    CustomSortProcess thread1 = new CustomSortProcess(peopleFirstHalf.toArray(new People[0]));
    CustomSortProcess thread2 = new CustomSortProcess(peopleSecondHalf.toArray(new People[0]));

    thread1.start();
    thread2.start();
    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    thread1.interrupt();
    thread2.interrupt();

    People [] sortedLeft = thread1.getInternal();
    People [] sortedRight = thread2.getInternal();
    People [] merged = new People[sortedLeft.length + sortedRight.length];
    mergedResult(sortedLeft, sortedRight, merged);
    printDataByThread(merged);
  }

  private void mergedResult(People [] left, People [] right, People [] returnedArray) {
    int i1 = 0;
    int i2 = 0;
    for (int i = 0; i < returnedArray.length; i++) {
      if (i2 >= right.length || (i1 < left.length && left[i1].getBirthDate().compareTo(right[i2].getBirthDate()) < 0)) {
        returnedArray[i] = left[i1];
        i1++;
      } else {
        returnedArray[i] = right[i2];
        i2++;
      }
    }
  }

  private void printDataByThread(People [] merged) {

  }
}
