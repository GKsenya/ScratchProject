package ScratchPad.WorkClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Month {
    private int month;
    private int year;
    private int days[][];
    private int numberOfWeeks;
    private static HashMap months = new HashMap();
    private List<Integer> leapYear = Arrays.asList(2020, 2024, 2028);
    private Month()
    {
    }

    private Month(int month, int year)
    {
        days = new int[6][7];
        numberOfWeeks = 0;
        this.month = month;
        this.year = year;
        buildWeeks();
    }

    public int getMonth()
    {
        return month;
    }

    public static Month getMonth(int month, int year)
    {
        String key = String.valueOf((new StringBuffer(String.valueOf(month))).append("/").append(year));
        if (months.containsKey(key))
        {
            return (Month) months.get(key);
        }
        else
        {
            Month newMonth = new Month(month, year);
            months.put(key, newMonth);
            return newMonth;
        }
    }

    private void buildWeeks()
    {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setFirstDayOfWeek(1);
        c.set(year, month, 1);
        for (; c.get(2) == month; c.add(5, 1))
        {
            int weekNumber = c.get(4) - 1;
            if(month == 1 || month == 4 || month == 7) {
                weekNumber++;
            }
            int dayOfWeek = calculateDay(c.get(7));
            days[weekNumber][dayOfWeek] = c.get(5);
            numberOfWeeks = weekNumber;
        }
    }

    public int calculateDay(int day)
    {
        if (day == 1)
            return 0;
        if (day == 2)
            return 1;
        if (day == 3)
            return 2;
        if (day == 4)
            return 3;
        if (day == 5)
            return 4;
        if (day == 6)
            return 5;
        return day != 7 ? 7 : 6;
    }

    public int[][] getDays()
    {
        return days;
    }

    public int getNumberOfWeeks()
    {
        return numberOfWeeks + 1;
    }

    public int getYear()
    {
        return year;
    }

}
