package Correct_Code;

import java.util.ArrayList;
import java.util.List;

// Game.java
public class Game {
    public static void main(String[] args) {
        ParticleTypeFactory factory = new ParticleTypeFactory();
        List<Particle> particles = new ArrayList<>();
        // Create thousands of particles using shared flyweights
        ParticleType explosionType = factory.getParticleType("red", "explosion.png");

        for (int i = 0; i < 10; i++) {
            particles.add(new Particle(explosionType,
                    (float) Math.random() * 100,
                    (float) Math.random() * 100,
                    1.0f));
        }
        // Update all particles
        for (Particle particle : particles) {
            particle.update();
        }
    }
}

/*

Output :
Rendering red particle at (21.416668,84.93594) with sprite explosion.png
Rendering red particle at (61.41813,91.2915) with sprite explosion.png
Rendering red particle at (36.802338,69.7141) with sprite explosion.png
Rendering red particle at (72.986206,27.286) with sprite explosion.png
Rendering red particle at (19.864418,15.151333) with sprite explosion.png
Rendering red particle at (77.864944,79.50962) with sprite explosion.png
Rendering red particle at (69.51494,57.23417) with sprite explosion.png
Rendering red particle at (70.96872,79.69071) with sprite explosion.png
Rendering red particle at (81.395744,33.05773) with sprite explosion.png
Rendering red particle at (3.8059301,8.231289) with sprite explosion.png

*/
