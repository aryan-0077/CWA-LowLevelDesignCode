package Generics.Advantages.Eliminates_Type_Casting.Solution;

import Generics.Advantages.Eliminates_Type_Casting.MyArrayList;

public class Test {
    public static void main(String[] args) {
        // Creating a an ArrayList with String specified
        MyArrayList<String> list = new MyArrayList<String>();
        list.add("Sachin");
        list.add("Rahul");
        // list.add(10);

        // Typecasting is not needed
        String s1 = list.get(0);
        String s2 = list.get(1);
        String s3 = list.get(2);
    }
}
