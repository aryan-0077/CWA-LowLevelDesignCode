package Good_Code;

import java.util.Stack;

public class EditorHistory {
    private Stack<Memento> history = new Stack<>();
    private Stack<Memento> undoStack = new Stack<>();
    private Stack<Memento> redoStack = new Stack<>();
    public void push(Memento memento) {
        history.push(memento);
    }

    public Memento pop() {
        if (!history.isEmpty()) return history.pop();
        return null;
    }

    // Save new state; clear redo stack when a new state is saved
    public void saveState(Memento memento) {
        undoStack.push(memento);
        redoStack.clear();
    }

    // Undo operation: push current state to redo stack and return last state from
    // undo stack
    public Memento undo(Memento currentState) {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentState);
            return undoStack.pop();
        }
        return null;
    }

    // Redo operation: push current state to undo stack and return last state from
    // redo stack
    public Memento redo(Memento currentState) {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentState);
            return redoStack.pop();
        }
        return null;
    }
}
