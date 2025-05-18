package Ugly_Code;

public class TextEditorUgly {
    private String text;
    // Imagine if we have multiple backups for multi-level undo!
    private String backup1;
    private String backup2;
    // ... and so on

    public void setText(String text) {
        // Before changing, we update backups manually
        backup2 = backup1;
        backup1 = this.text;
        this.text = text;
    }

    public void undo() {
        // Only supports one level of undo correctly
        this.text = backup1;
    }

    public void showText() {
        System.out.println("Current text: " + text);
    }
}
