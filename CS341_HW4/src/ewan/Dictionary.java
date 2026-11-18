package ewan;

public class Dictionary {

    private Node root;

    public void insert(String word) {
        root = insert(root, word.toLowerCase());
    }

    private Node insert(Node node, String word) {
        if (node == null) return new Node(word);

        int cmp = word.compareTo(node.word);
        if (cmp < 0) node.left = insert(node.left, word);
        else if (cmp > 0) node.right = insert(node.right, word);

        return node;
    }

    public boolean contains(String word) {
        word = word.toLowerCase();
        Node current = root;
        while (current != null) {
            int cmp = word.compareTo(current.word);
            if (cmp == 0) return true;
            current = (cmp < 0) ? current.left : current.right;
        }
        return false;
    }

    public boolean spellCheck(String word) {
        word = word.toLowerCase();
        Node current = root;
        while (current != null) {
            int cmp = word.compareTo(current.word);
            if (cmp == 0) return true;
            current = (cmp < 0) ? current.left : current.right;
        }
        return false;
    }

    public void remove(String word) {
        root = remove(root, word.toLowerCase());
    }

    private Node remove(Node node, String word) {
        if (node == null) return null; // word not found

        int cmp = word.compareTo(node.word);
        if (cmp < 0) {
            node.left = remove(node.left, word);
        } else if (cmp > 0) {
            node.right = remove(node.right, word);
        } else {
            // Found the node to delete
            if (node.left == null) return node.right;   // case 1 or 2
            if (node.right == null) return node.left;   // case 2

            // Case 3: two children
            Node successor = findMin(node.right);
            node.word = successor.word;
            node.right = remove(node.right, successor.word);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}







