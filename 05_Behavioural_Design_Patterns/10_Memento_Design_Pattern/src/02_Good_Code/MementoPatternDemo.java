package Good_Code;

public class MementoPatternDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();

        // Initial state
        editor.setText("Hello");
        history.saveState(editor.save());
        System.out.println("Initial text: " + editor.getText());

        // First change
        editor.setText("Hello, World!");
        history.saveState(editor.save());
        System.out.println("After first change: " + editor.getText());

        // Second change
        editor.setText("Hello, World! Welcome!");
        history.saveState(editor.save());
        System.out.println("After second change: " + editor.getText());

        // Undo last change
        Memento previousState = history.undo(editor.save());
        if (previousState != null) {
            editor.restore(previousState);
            System.out.println("After undo: " + editor.getText());
        }

        // Redo the undone change
        Memento redoState = history.redo(editor.save());
        if (redoState != null) {
            editor.restore(redoState);
            System.out.println("After redo: " + editor.getText());
        }
    }
}

/*

Output 1:
Current text: Hello
Current text: Hello, World!
Current text: Hello, World! Welcome to Memento Pattern.
After undo, text: Hello, World!
After second undo, text: Hello


Output 2 :
Current: Hello, World! Welcome!
After undo: Hello, World!
After redo: Hello, World! Welcome!

*/