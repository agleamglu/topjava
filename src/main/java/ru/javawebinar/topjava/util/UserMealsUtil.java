package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        //        .toLocalDate();
//        .toLocalTime();
    }
// stream
    private static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        int sum = 0;
        for (UserMeal userMeal : mealList) {
            int calories = userMeal.getCalories();
            sum += calories;
        }
        int i = sum;
        boolean exc = false;
        if (i > caloriesPerDay) exc = true;
        final boolean ex = exc;
        return mealList
                .stream()
                .filter(s -> (s.getDateTime().toLocalTime().isAfter(startTime) && s.getDateTime().toLocalTime().isBefore(endTime)))
                .map(p -> new UserMealWithExceed(p.getDateTime(), p.getDescription(), p.getCalories(), ex))
                .collect(Collectors.toList());
    }

    //for loop
    private static List<UserMealWithExceed> getFilteredWithExceeded1(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> filteredList=new ArrayList<>();

        int sum = 0;
        for (UserMeal s : mealList) {
            sum += s.getCalories();
        }
        int i= sum;
        boolean exc=false;
        if (i>caloriesPerDay) exc=true;
        final Boolean ex=exc;

        for (UserMeal um:mealList) {
            if (um.getDateTime().toLocalTime().isAfter(startTime)&&um.getDateTime().toLocalTime().isBefore(endTime)){
                i+=um.getCalories();
                UserMealWithExceed ume = new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(),ex);
                filteredList.add(ume);
            }
        }

        return filteredList;
    }


}
