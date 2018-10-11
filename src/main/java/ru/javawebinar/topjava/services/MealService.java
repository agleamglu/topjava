package ru.javawebinar.topjava.services;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

public class MealService {
    private MealDao mealDao = new MealDao();

    public MealService() {
    }

    public List<MealWithExceed> getFullListOfMeal() {
        return mealDao.getFullListOfMeal();
    }

    public void deleteRecord(int id) {
        mealDao.deleteRecord(id);
    }

    public void addNewRecord(LocalDateTime dateTime, String description, int calories) {
        mealDao.addNewRecord(dateTime, description, calories);
    }

    public void addNewRecordFromString(String dateTimeStr, String description, String caloriesStr) {
        mealDao.addNewRecordFromString(dateTimeStr, description, caloriesStr);
    }

    public Meal getRecordById(int id) {
        return mealDao.getRecordById(id);
    }

}
