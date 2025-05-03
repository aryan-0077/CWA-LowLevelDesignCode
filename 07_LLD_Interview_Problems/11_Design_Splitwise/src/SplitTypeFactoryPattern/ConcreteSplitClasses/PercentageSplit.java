package SplitTypeFactoryPattern.ConcreteSplitClasses;

import SplitTypeFactoryPattern.Split;
import UtilityClasses.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Implementation of the Split interface for percentage-based split
public class PercentageSplit implements Split {
    @Override
    public Map<User, Double> calculateSplit(double amount, List<User> participants, Map<String, Object> splitDetails) {
        // Retrieve the percentage allocation for each participant from the split details
        Map<User, Double> percentages = (Map<User, Double>) splitDetails.get("percentages");
        Map<User, Double> splits = new HashMap<>(); // Map to hold the calculated split


        for (User user : participants) {
            double percentage = percentages.getOrDefault(user, 0.0); // Get the percentage for the user
            splits.put(user, amount * percentage / 100.0); // Calculate the share based on the percentage
        }
        return splits;
    }
}
