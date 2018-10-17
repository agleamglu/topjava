package ru.javawebinar.topjava.util;


import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class ValidationUtil {

    public static final List<User> USERS = Arrays.asList(
            new User("Инга Кораблева", "inga@gmail.com", "123456", Role.ROLE_USER),
            new User("Маршак С.Я.", "marshak@mail.ru", "123456", Role.ROLE_ADMIN),
            new User("Старков Иван Петрович", "starkov@gmail.com", "123456", Role.ROLE_ADMIN, Role.ROLE_USER)
    );

    public static List<User> getSortedList(Collection<User> users) {
        Collections.sort(users.stream().collect(Collectors.toList()), Comparator.comparing(User::getName));
        return users.stream().collect(Collectors.toList());
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}