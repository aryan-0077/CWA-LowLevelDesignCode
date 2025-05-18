package Ugly_Code;

// Particle.java
public class Particle {
    private String color;
    private String sprite;
    private float x;
    private float y;
    private float velocity;
    public Particle(
            String color, String sprite, float x, float y, float velocity) {
        this.color = color;
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }

    public void update() {
        // Update particle position
        y += velocity;
        System.out.println(
                "Particle at position (" + x + "," + y + ") with color " + color);
    }
}
