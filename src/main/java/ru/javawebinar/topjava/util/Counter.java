package ru.javawebinar.topjava.util;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger count = new AtomicInteger(1);

    public int getAndIncrementId() {
        return count.getAndIncrement();

    }
    public int value(){
        return count.get();
    }
}
