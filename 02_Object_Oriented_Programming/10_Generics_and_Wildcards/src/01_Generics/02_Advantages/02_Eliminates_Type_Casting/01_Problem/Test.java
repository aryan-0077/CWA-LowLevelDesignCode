package Generics.Advantages.Eliminates_Type_Casting.Problem;

import Generics.Advantages.Eliminates_Type_Casting.MyArrayList;

public class Test {
    public static void main(String[] args) {
        // Creating an ArrayList without any type specified
        MyArrayList list = new MyArrayList();
        list.add("Sachin");
        list.add("Rahul");
        list.add(10); // Compiler allows this
        String s1 = (String) list.get(0);
        String s2 = (String) list.get(1);
        // Causes Runtime Exception
        String s3 = (String) list.get(2);
    }
}
