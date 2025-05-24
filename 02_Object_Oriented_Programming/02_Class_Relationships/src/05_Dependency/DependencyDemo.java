package Dependency;

public class DependencyDemo {
    public static void main(String[] args) {
        Document doc = new Document("Hello, World!");
        Printer printer = new Printer();
        doc.printDocument(printer);
    }
}

/*
Output :
Printing: Hello, World!
*/