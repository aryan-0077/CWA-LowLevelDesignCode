package SchedulingAlgoStrategyPattern;

import UtilityClasses.Elevator;

public interface SchedulingStrategy {
    // Determines the next stop for the given elevator
    int getNextStop(Elevator elevator);
}
