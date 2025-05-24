package Generics.Advantages.Eliminates_Type_Casting;

public class MyArrayList<T> {
    private Object[] elements;
    private int size = 0;
    public MyArrayList() {
        elements = new Object[10]; // Default capacity
    }

    public void add(T element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) elements[index];
    }

    private void resize() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    public static void main(String[] args) {
        // Without explicit type declaration
        Generics.Advantages.Readability_Maintainability.MyArrayList list1 = new Generics.Advantages.Readability_Maintainability.MyArrayList();
        list1.add("Hello");
        list1.add("World");
        list1.add(1);
        list1.add('a');

// With explicit type declaration
        Generics.Advantages.Readability_Maintainability.MyArrayList<String> list2 = new Generics.Advantages.Readability_Maintainability.MyArrayList<>();
        list2.add("Hello");
        list2.add("World");
    }
}
