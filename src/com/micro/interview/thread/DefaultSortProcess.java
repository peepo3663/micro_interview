package com.micro.interview.thread;

import com.micro.interview.models.People;
import com.micro.interview.models.SortByDateComparator;
import java.util.List;

public class DefaultSortProcess implements Runnable {
  private List<People> peopleList;

  public DefaultSortProcess(List<People> peopleList) {
    this.peopleList = peopleList;
  }

  @Override
  public void run() {
    peopleList.sort(new SortByDateComparator());
  }

  public List<People> getPeopleList() {
    return peopleList;
  }
}
