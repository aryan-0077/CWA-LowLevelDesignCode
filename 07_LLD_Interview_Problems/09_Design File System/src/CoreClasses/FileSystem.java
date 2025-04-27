package CoreClasses;

import CompositePattern.ConcreteImplementations.Directory;
import CompositePattern.ConcreteImplementations.File;
import CompositePattern.FileSystemNode;

// Main File System class implementing the trie structure
public class FileSystem {
    // Root directory
    private FileSystemNode root;

    // Constructor to initialize the file system with a root directory
    public FileSystem() {
        this.root = new Directory("/");
    }
    // path = "/document/cwa_lld/design_file_system"
    // Checking if path is not null, not empty, and starts with
    // Validate file path to be non-empty and properly formatted
    public boolean isValidFilePath(String path) {
        // Returns true because path meets all criteria
        return path != null && !path.isEmpty() && path.startsWith("/");
    }

    // Create a new path
    // path = "/document/cwa_lld/design_file_system"
    public boolean createPath(String path) {
        // Validate path
        // path is valid, so continue
        if (!isValidFilePath(path))
            return false;
        // Split path into components
        // pathComponents = ["", "document", "cwa_lld", "design_file_system"]
        String[] pathComponents = path.split("/");
        // Start from root
        // current = root directory "/"
        FileSystemNode current = root;
        // Traverse to the parent directory
        // We need to process: "document" and "cwa_lld" (stopping before the last component)
        for (int i = 1; i < pathComponents.length - 1; i++) {
            String component = pathComponents[i];
            if (component.isEmpty())
                continue; // Skip empty components
            // First iteration: component = "document"
            // Second iteration: component = "cwa_lld"
            if (!current.hasChild(component)) {
                // If "document" doesn't exist, create it
                // If "cwa_lld" doesn't exist, create it
                FileSystemNode newDir = new Directory(component);
                current.addChild(component, newDir);
            }
            FileSystemNode child = current.getChild(component);
            if (child.isFile()) {
                // If "document" or "cwa_lld" is a file, we cannot navigate through it
                // Return false in that case
                return false;
            }
            // Move to the next level
            // First iteration: current = "document" directory
            // Second iteration: current = "cwa_lld" directory
            current = child;
        }
        // Get the last component (file or directory name)
        // lastComponent = "design_file_system"
        String lastComponent = pathComponents[pathComponents.length - 1];
        if (lastComponent.isEmpty())
            return false;
        // Check if the component already exists
        // If "design_file_system" already exists under "cwa_lld", return false
        if (current.hasChild(lastComponent)) {
            return false;
        }
        // Create new node based on whether it's a file (has extension) or directory
        // "design_file_system" has no dot, so create as directory
        FileSystemNode newNode;
        if (lastComponent.contains(".")) {
            newNode = new File(lastComponent);
        } else {
            newNode = new Directory(lastComponent);
        }
        // Add the new node to the parent
        // Add "design_file_system" directory to "cwa_lld"
        current.addChild(lastComponent, newNode);
        return true;
    }

    // Helper method to get node at path
    // path = "/document/cwa_lld/design_file_system"
    private FileSystemNode getNode(String path) {
        // Check if path is valid
        // Path is valid, so continue
        if (!isValidFilePath(path))
            return null;
        // For root path
        // Path is not "/", so skip this
        if (path.equals("/"))
            return root;
        // Split path into components
        // pathComponents = ["", "document", "cwa_lld", "design_file_system"]
        String[] pathComponents = path.split("/");
        // Start from root
        // current = root directory "/"
        FileSystemNode current = root;
        // Traverse through the path
        // We need to process: "document", "cwa_lld", and "design_file_system"
        for (int i = 1; i < pathComponents.length; i++) {
            String component = pathComponents[i];
            if (component.isEmpty())
                continue; // Skip empty components
            // First iteration: component = "document"
            // Second iteration: component = "cwa_lld"
            // Third iteration: component = "design_file_system
            if (!current.hasChild(component)) {
                // If any component doesn't exist at its level, return null
                return null;
            }
            // Move to the next level
            // First iteration: current = "document" directory
            // Second iteration: current = "cwa_lld" directory
            // Third iteration: current = "design_file_system" directory
            current = current.getChild(component);
        }
        // Return the node found at the path
        // Returns the "design_file_system" directory node
        return current;
    }

    // Delete path
    public boolean deletePath(String path) {
        // path = "/document/cwa_lld/design_file_system"
        // Check if path is valid
        // Path is valid, so continue
        if (!isValidFilePath(path))
            return false;
        // Can't delete root
        // Path is not "/", so continue
        if (path.equals("/"))
            return false;
        // Get parent path
        // parentPath = "/document/cwa_lld"
        String parentPath = getParentPath(path);
        // Get the parent node
        // parent = "cwa_lld" directory node
        FileSystemNode parent = getNode(parentPath);
        // If parent doesn't exist or is a file, can't delete
        // Assuming parent exists and is a directory, continue
        if (parent == null || parent.isFile())
            return false;
        // Get the last component of the path
        // lastComponent = "design_file_system"
        String lastComponent = path.substring(path.lastIndexOf('/') + 1);
        // Check if the component exists
        // If "design_file_system" doesn't exist under "cwa_lld", return false
        if (!parent.hasChild(lastComponent)) {
            return false;
        }
        // Remove the child from the parent
        // Remove "design_file_system" from "cwa_lld"
        return parent.removeChild(lastComponent);
    }

    // Helper method to get parent path
    private String getParentPath(String path) {
        // path = "/document/cwa_lld/design_file_system"
        // Find the last slash
        // lastSlash = 19 (position of last slash before "design_file_system")
        int lastSlash = path.lastIndexOf('/');
        // If there's no slash or only the root slash, return root
        // lastSlash is 19, which is > 0, so continue
        if (lastSlash <= 0) {
            return "/";
        }
        // Return the substring up to the last slash
        // Returns "/document/cwa_lld"
        return path.substring(0, lastSlash);
    }

    // Display the entire file system structure
    public void display() {
        root.display(0);
    }

    // Set content for file
    public boolean setFileContent(String path, String content) {
        FileSystemNode node = getNode(path);
        if (node == null || !node.isFile())
            return false;
        File file = (File) node;
        file.setContent(content);
        return true;
    }

    // Get content from file
    public String getFileContent(String path) {
        FileSystemNode node = getNode(path);
        if (node == null || !node.isFile())
            return null;
        File file = (File) node;
        return file.getContent();
    }
}