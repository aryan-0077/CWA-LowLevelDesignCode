package PrivateConstructor;

public class Singleton {
    private static Singleton instance;
    // Private constructor
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}