package Examples.CopyOnWriteArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListExample {
    public static void main(String[] args) throws InterruptedException {
        // --- Example 1: Using a plain ArrayList (Not Thread-Safe) ---
        // Initialize the ArrayList with some values.
        final List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        Thread arrayListWriter = new Thread(() -> {
            try {
                // Sleep to let the reader start iterating
                Thread.sleep(50);
                for (int i = 4; i <= 6; i++) {
                    System.out.println("ArrayList Writer adding: " + i);
                    arrayList.add(i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread arrayListReader = new Thread(() -> {
            try {
                // Wait a bit so that writer thread starts its job
                Thread.sleep(25);

                // This loop is likely to throw ConcurrentModificationException
                // because the list is modified during iteration.
                for (Integer item : arrayList) {
                    System.out.println("ArrayList Reader read: " + item);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                // Catching ConcurrentModificationException or any other exception
                System.out.println("Exception in ArrayList Reader: " + e);
            }
        });
        System.out.println("Using ArrayList:");
        arrayListWriter.start();
        arrayListReader.start();
        arrayListWriter.join();
        arrayListReader.join();
        // Print final state of ArrayList
        System.out.println("Final ArrayList: " + arrayList);

        // --- Example 2: Using a CopyOnWriteArrayList (Thread-Safe) ---
        // Initialize the CopyOnWriteArrayList with the same initial values.
        final CopyOnWriteArrayList<Integer> cowList = new CopyOnWriteArrayList<>();
        cowList.add(1);
        cowList.add(2);
        cowList.add(3);

        Thread cowListWriter = new Thread(() -> {
            try {
                // Sleep to let the reader start iterating on the initial snapshot
                Thread.sleep(50);
                for (int i = 4; i <= 6; i++) {
                    System.out.println("CopyOnWriteArrayList Writer adding: " + i);
                    cowList.add(i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread cowListReader = new Thread(() -> {
            try {
                // Wait a little so that the writer thread may add new elements
                // (but the iterator is created before those new elements are added)
                Thread.sleep(25);
                // Iterating here is safe; the iterator will see a snapshot of the list.
                for (Integer item : cowList) {
                    System.out.println("CopyOnWriteArrayList Reader read: " + item);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        System.out.println("nUsing CopyOnWriteArrayList:");
        cowListWriter.start();
        cowListReader.start();
        cowListWriter.join();
        cowListReader.join();
        // Print final state of CopyOnWriteArrayList
        System.out.println("Final CopyOnWriteArrayList: " + cowList);
    }
}

/*

Output :
Using ArrayList:
ArrayList Reader read: 1
ArrayList Writer adding: 4
Exception in ArrayList Reader: java.util.ConcurrentModificationException
ArrayList Writer adding: 5
ArrayList Writer adding: 6
Final ArrayList: [1, 2, 3, 4, 5, 6]
nUsing CopyOnWriteArrayList:
CopyOnWriteArrayList Reader read: 1
CopyOnWriteArrayList Writer adding: 4
CopyOnWriteArrayList Reader read: 2
CopyOnWriteArrayList Writer adding: 5
CopyOnWriteArrayList Reader read: 3
CopyOnWriteArrayList Writer adding: 6
Final CopyOnWriteArrayList: [1, 2, 3, 4, 5, 6]

*/
