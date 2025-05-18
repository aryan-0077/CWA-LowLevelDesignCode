package Correct_Code;

import java.util.HashMap;
import java.util.Map;

// ParticleTypeFactory.java
public class ParticleTypeFactory {
    private Map<String, ParticleType> particleTypes = new HashMap<>();
    public ParticleType getParticleType(String color, String sprite) {
        String key = color + "_" + sprite;
        return particleTypes.computeIfAbsent(key, k -> new ParticleType(color, sprite));
    }
}