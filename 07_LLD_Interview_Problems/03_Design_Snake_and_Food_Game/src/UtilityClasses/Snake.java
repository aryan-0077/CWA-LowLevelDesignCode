package UtilityClasses;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Snake {
        private Deque<Pair> body; // Snake body as a deque of positions
        private Map<Pair, Boolean> positionMap; // For O(1) collision check
        public Snake() {
            this.body = new LinkedList<>();
            this.positionMap = new HashMap<>();
            // Initialize snake at position [0,0]
            Pair initialPos = new Pair(0, 0);
            this.body.offerFirst(initialPos);
            this.positionMap.put(initialPos, true);
        }

}
