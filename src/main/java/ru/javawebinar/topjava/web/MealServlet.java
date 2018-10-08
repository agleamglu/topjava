package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.slf4j.LoggerFactory.getILoggerFactory;
import static org.slf4j.LoggerFactory.getLogger;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.model.Meal;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        MealDao mealDao = new MealDao();

        List<MealWithExceed> mealList = mealDao.getFullListOfMeal();

        request.setAttribute("list", mealList);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
        //response.sendRedirect("meals.jsp");
    }
}
