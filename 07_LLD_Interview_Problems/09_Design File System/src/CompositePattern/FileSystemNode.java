package CompositePattern;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class FileSystemNode {
    // Name of the node
    private String name;
    // Map of child nodes
    private Map<String, FileSystemNode> children;
    // Timestamp for node creation
    private LocalDateTime createdAt;
    // Timestamp for the last modification
    private LocalDateTime modifiedAt;
    // Constructor to initialize the node with a name
    public FileSystemNode(String name) {
        this.name = name;
        this.children = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    // Add child node
    public void addChild(String name, FileSystemNode child) {
        this.children.put(name, child);
        this.modifiedAt = LocalDateTime.now();
    }

    // Check if child exists
    public boolean hasChild(String name) {
        return this.children.containsKey(name);
    }

    // Get child node by name
    public FileSystemNode getChild(String name) {
        return this.children.get(name);
    }

    // Remove child node
    public boolean removeChild(String name) {
        if (hasChild(name)) {
            children.remove(name);
            return true;
        }
        return false;
    }

    // Abstract methods for node operations
    public abstract boolean isFile();
    public abstract void display(int depth);

    // Getters and Setters
    public String getName() {
        return name;
    }

    public Collection<FileSystemNode> getChildren() {
        return children.values();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    // Update the modification timestamp
    protected void updateModifiedTime() {
        this.modifiedAt = LocalDateTime.now();
    }
}
