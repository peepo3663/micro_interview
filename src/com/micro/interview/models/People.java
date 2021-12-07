package com.micro.interview.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class People {
  private int id;
  private String idString;
  private String day;
  private String month;
  private String year;
  private Date birthDate;

  public People(String data) {
    String [] details = data.split(" ");
    this.idString = details[0];
    this.id = Integer.parseInt(idString);
    this.month = details[1];
    this.day = details[2];
    this.year = details[3];
    int month = Integer.parseInt(this.month);
    int day = Integer.parseInt(this.day);
    int year = Integer.parseInt(this.year);
    LocalDate localDate = LocalDate.of(year, month, day);
    this.birthDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getIdString() {
    return idString;
  }

  public String getDay() {
    return day;
  }

  public String getMonth() {
    return month;
  }

  public String getYear() {
    return year;
  }
}
