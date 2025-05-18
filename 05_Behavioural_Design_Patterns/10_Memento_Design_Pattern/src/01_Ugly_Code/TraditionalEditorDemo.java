package Ugly_Code;

public class TraditionalEditorDemo {
    public static void main(String[] args) {
        TextEditorTraditional editor = new TextEditorTraditional("Hello");
        editor.showText(); // Output: Hello
        // User makes a change
        String backup = "Hello"; // Manually keeping the backup 😅
        editor.setText("Hello, World!");
        editor.showText(); // Output: Hello, World!
        // Undo the change by manually restoring the backup
        editor.undo(backup);
        editor.showText(); // Output: Hello
    }
}

/*

Output :
Current text: Hello
Current text: Hello, World!
Current text: Hello

*/