package MovementStrategyPackage;

import UtilityClasses.Pair;

// Movement strategy interface
public interface MovementStrategy {
    Pair getNextPosition(Pair currentHead, String direction);
}