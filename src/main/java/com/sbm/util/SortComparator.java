package com.sbm.util;

import java.util.Comparator;

/**
 * Sort comparator class.
 * @author HCL
 */
public class SortComparator implements Comparator<String> {

  String sortOrder = "";

  public SortComparator(String s) {
    this.sortOrder = s;
  }

  /**
   * Returns integer based on sortOrder and take only digits.
   * @param str1
   * @param str2
   * @return int
   */
  @Override
  public int compare(String str1, String str2) {
    String notDigit = "[^\\d$]";
    int int1 = Integer.parseInt(str1.replaceAll(notDigit, ""));
    int int2 = Integer.parseInt(str2.replaceAll(notDigit, ""));
    return this.sortOrder.equalsIgnoreCase("asc") ? Integer.compare(int1, int2) : Integer.compare(int2, int1);
  }
}
