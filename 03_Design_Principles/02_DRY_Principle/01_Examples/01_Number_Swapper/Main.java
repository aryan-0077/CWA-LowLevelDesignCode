package Examples.Number_Swapper;

public class Main {
    public static void main(String[] args) {
        NumberSwapper swapper = new NumberSwapper();
        int[] numbers = {5, 10};
        System.out.println("Before swap: " + numbers[0] + ", " + numbers[1]);
        swapper.swap(numbers);
        System.out.println("After swap: " + numbers[0] + ", " + numbers[1]);
    }
}
