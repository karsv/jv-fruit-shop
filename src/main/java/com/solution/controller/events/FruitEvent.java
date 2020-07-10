package com.solution.controller.events;

import com.solution.exceptions.FruitEventException;

public interface FruitEvent {
    void execute() throws FruitEventException;
}
