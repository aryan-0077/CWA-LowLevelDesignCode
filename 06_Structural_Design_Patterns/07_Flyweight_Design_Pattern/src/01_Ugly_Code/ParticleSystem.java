package Ugly_Code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticleSystem {
    private Map<String, String> spriteCache = new HashMap<>();
    private List<Particle> particles = new ArrayList<>();
    public void createParticle(
            String color, String spritePath, float x, float y, float velocity) {
        // Try to reuse sprite from cache
        String sprite =
                spriteCache.computeIfAbsent(spritePath, path -> loadSprite(path));
        particles.add(new Particle(color, sprite, x, y, velocity));
    }

    private String loadSprite(String path) {
        // Simulate loading sprite
        return "Loaded: " + path;
    }
}