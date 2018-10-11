package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.RequestDispatcher;
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
import ru.javawebinar.topjava.services.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.model.Meal;


public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static String MEALS_PAGE = "meals.jsp";
    private MealService mealService;

    public void init() {
        try {
            super.init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        mealService = new MealService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        //response.sendRedirect("meals.jsp");

        String forward;
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        // switch (action){
        //    case "delete":
        //        break;
        //    case "update":
        //        break;

        // }

        if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            mealService.deleteRecord(id);
            forward = MEALS_PAGE;
            request.setAttribute("list", mealService.getFullListOfMeal());
        } else if (action.equalsIgnoreCase("add_new")) {
            forward = "editMeal.jsp";
            request.setAttribute("list", "");
        } else if (action.equalsIgnoreCase("edit")) {
            forward = "editMeal.jsp";
            request.setAttribute("list", mealService.getFullListOfMeal());
        } else if (action.equalsIgnoreCase("list")) {
            forward = MEALS_PAGE;
            request.setAttribute("list", mealService.getFullListOfMeal());
        } else {
            forward = MEALS_PAGE;
            request.setAttribute("list", mealService.getFullListOfMeal());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String id = request.getParameter("id");
        if (id == null) {
            mealService.addNewRecordFromString(request.getParameter("mealDate") + " " + request.getParameter("mealTime"),
                    request.getParameter("mealType"), request.getParameter("calories"));

            forward = MEALS_PAGE;
            request.setAttribute("list", mealService.getFullListOfMeal());
        } else {
            forward = "editMeal.jsp";
            request.setAttribute("record", mealService.getRecordById(Integer.parseInt(id)));
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }


}
