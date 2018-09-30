package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import java.util.*;
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
        Map<LocalDate, Integer> currentCalories = mealList.stream()
                .collect(Collectors.toMap(e -> e.getDateTime().toLocalDate(),
                        UserMeal::getCalories,
                        (dailyCalories, currentMealCalories) -> dailyCalories + currentMealCalories));
        currentCalories.entrySet().forEach(System.out::println);
        return mealList
                .stream()
                .filter(s -> TimeUtil.isBetween(s.getDateTime().toLocalTime(), startTime, endTime))
                .map(p -> new UserMealWithExceed(p.getDateTime(), p.getDescription(), p.getCalories(),
                        currentCalories.get(p.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    //for loop
    private static List<UserMealWithExceed> getFilteredWithExceeded1(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> hashMap = calories(mealList);
        List<UserMealWithExceed> filteredList = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                UserMealWithExceed userMealWithExceed = new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                        (hashMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
                filteredList.add(userMealWithExceed);
            }
        }
        return filteredList;
    }

    //total amount of calories per day
    private static Map<LocalDate, Integer> calories(List<UserMeal> mealList) {
        Map<LocalDate, Integer> dailyCaloriesMap = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            dailyCaloriesMap.put(userMeal.getDateTime().toLocalDate(),
                    dailyCaloriesMap.getOrDefault(userMeal.getDateTime().toLocalDate(), 0) + userMeal.getCalories());
        }
        return dailyCaloriesMap;
    }


}
