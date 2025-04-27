package CompositePattern.ConcreteImplementations;

import CompositePattern.FileSystemNode;

public class File extends FileSystemNode {
    // Content of the file
    private String content;
    // File extension
    private String extension;

    // Constructor for file with name
    public File(String name) {
        super(name);
        this.extension = extractExtension(name);
    }

    // Extract file extension from name
    private String extractExtension(String name) {
        int dotIndex = name.lastIndexOf('.');
        return (dotIndex > 0) ? name.substring(dotIndex + 1) : "";
    }

    // Set content of the file
    public void setContent(String content) {
        this.content = content;
        updateModifiedTime();
    }

    // Get content of the file
    public String getContent() {
        return content;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public void display(int depth) {
        // Example: For a file at path "/document/cwa_lld/requirements.txt" at depth 3
        // indent = "      " (6 spaces: depth 3 * 2 spaces per depth)
        // Output would be: "      📄 requirements.txt"
        // For our example, if depth is 3 (meaning this file is at the 3rd level)
        // Generate indent string of 6 spaces (3*2)
        String indent = " ".repeat(depth * 2);
        // Print the file with appropriate indentation and emoji
        // e.g., "      📄 requirements.txt"
        System.out.println(indent + "📄 " + getName());
    }
}
