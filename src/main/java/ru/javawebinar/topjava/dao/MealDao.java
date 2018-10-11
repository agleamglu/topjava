package ru.javawebinar.topjava.dao;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.Counter;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealDao {

    private List<Meal> meals = new ArrayList<>();
    private Counter id;

    public MealDao() {
        id = new Counter();
        meals.addAll(Arrays.asList(
                new Meal(id.getAndIncrementId(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(id.getAndIncrementId(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(id.getAndIncrementId(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(id.getAndIncrementId(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(id.getAndIncrementId(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1500),
                new Meal(id.getAndIncrementId(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        ));
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public List<MealWithExceed> getFullListOfMeal() {
        return MealsUtil.getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, 2000);
    }

    public void deleteRecord(int id) {
        meals.removeIf(e -> e.getId() == id);
        //  meals.stream().filter(e->e.getId()==id).collect(Collectors.toList());
    }

    public void addNewRecord(LocalDateTime dateTime, String description, int calories) {
        meals.add(new Meal(id.getAndIncrementId(), dateTime, description, calories));
    }

    public void addNewRecordFromString(String dateTimeStr, String description, String caloriesStr) {
        meals.add(new Meal(id.getAndIncrementId(), LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                description, Integer.parseInt(caloriesStr)));
    }

    public Meal getRecordById(int id) {
        return meals.stream().filter(e -> e.getId() == id)
                .findAny()
                .orElse(null);
    }

}

