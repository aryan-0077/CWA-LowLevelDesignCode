package Ugly_Code;

public class TextEditorTraditional {
    private String text;
    public TextEditorTraditional(String text) {
        this.text = text;
    }
    public void setText(String text) {
        // Directly update the text
        this.text = text;
    }
    // A makeshift "undo" that takes a previous state manually
    public void undo(String previousState) {
        this.text = previousState;
    }
    public void showText() {
        System.out.println("Current text: " + text);
    }
}
