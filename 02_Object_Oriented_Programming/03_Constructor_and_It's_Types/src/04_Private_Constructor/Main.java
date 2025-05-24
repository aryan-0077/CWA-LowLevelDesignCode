package PrivateConstructor;


public class Main {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2); // Output: true, as both references point to the same instance
    }
}
/*
Output :
true
*/