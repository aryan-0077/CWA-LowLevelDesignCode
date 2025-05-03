package SplitTypeFactoryPattern;

import SplitTypeFactoryPattern.ConcreteSplitClasses.EqualSplit;
import SplitTypeFactoryPattern.ConcreteSplitClasses.PercentageSplit;

public class SplitFactory {
    /**
     - Factory method to create a Split instance based on the specified split type.
     - 	     - @param splitType The type of split to create ("EQUAL", "PERCENTAGE", "EXACT").
     - @return An instance of the corresponding Split implementation.
     - @throws IllegalArgumentException if the split type is unknown.
     */
    public static Split createSplit(String splitType) {
        switch (splitType) {
            case "EQUAL":
                return new EqualSplit(); // Return an EqualSplit instance
            case "PERCENTAGE":
                return new PercentageSplit(); // Return a PercentageSplit instance
            default:
                // Throw an exception if the split type is invalid
                throw new IllegalArgumentException("Unknown split type: " + splitType);
        }
    }
}
