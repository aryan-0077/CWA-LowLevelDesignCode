package Dependency;

public class Document {
    String content;
    Document(String content) {
        this.content = content;
    }
    // Dependency: Document uses Printer to print its content.
    public void printDocument(Printer printer) {
        printer.print(content);
    }
}