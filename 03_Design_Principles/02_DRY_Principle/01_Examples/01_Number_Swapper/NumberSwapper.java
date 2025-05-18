package Examples.Number_Swapper;

public class NumberSwapper {

    // Non-DRY approach - repeating swap logic multiple places
    public void processNumbersNonDry() {
        int a = 5;
        int b = 10;
        // Swapping values here
        int temp = a;
        a = b;
        b = temp;
        // Later in the code, need to swap different numbers
        int x = 20;
        int y = 30;
        // Repeating the same swap logic
        temp = x;
        x = y;
        y = temp;
    }

    // DRY approach - creating a reusable swap method
    public void swap(int[] numbers) {
        if (numbers.length >= 2) {
            int temp = numbers[0];
            numbers[0] = numbers[1];
            numbers[1] = temp;
        }
    }
}