import java.util.Iterator;
import java.util.Stack;

class BinaryTree<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(T value) {
        this.root = new Node<>(value);
    }

    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            insertRecursive(root, value);
        }
    }

    private void insertRecursive(Node<T> node, T value) {
        if (value.compareTo(node.getValue()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(value));
            } else {
                insertRecursive(node.getLeft(), value);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new Node<>(value));
            } else {
                insertRecursive(node.getRight(), value);
            }
        }
    }

    public void inorderTraverse(Visitor<T> visitor) {
        inorderTraverseRecursive(root, visitor);
    }

    private void inorderTraverseRecursive(Node<T> node, Visitor<T> visitor) {
        if (node != null) {
            inorderTraverseRecursive(node.getLeft(), visitor);
            visitor.visit(node.getValue());
            inorderTraverseRecursive(node.getRight(), visitor);
        }
    }

    public void postorderTraverse(Visitor<T> visitor) {
        postorderTraverseRecursive(root, visitor);
    }

    private void postorderTraverseRecursive(Node<T> node, Visitor<T> visitor) {
        if (node != null) {
            postorderTraverseRecursive(node.getLeft(), visitor);
            postorderTraverseRecursive(node.getRight(), visitor);
            visitor.visit(node.getValue());
        }
    }

    public void preorderTraverse(Visitor<T> visitor) {
        preorderTraverseRecursive(root, visitor);
    }

    private void preorderTraverseRecursive(Node<T> node, Visitor<T> visitor) {
        if (node != null) {
            visitor.visit(node.getValue());
            preorderTraverseRecursive(node.getLeft(), visitor);
            preorderTraverseRecursive(node.getRight(), visitor);
        }
    }

    public int getSize() {
        return getSizeOfNode(root);
    }

    private int getSizeOfNode(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int leftSize = getSizeOfNode(node.getLeft());
        int rightSize = getSizeOfNode(node.getRight());
        return leftSize + rightSize + 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new InorderIterator();
    }

    // Nested class for Inorder Iterator
    private class InorderIterator implements Iterator<T> {
        private Node<T> current;
        private Stack<Node<T>> stack;

        public InorderIterator() {
            current = root;
            stack = new Stack<>();

            // Traverse to the leftmost node and push nodes onto the stack
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            Node<T> node = stack.pop();
            T value = node.getValue();

            // Process the right subtree
            if (node.getRight() != null) {
                node = node.getRight();

                // Traverse to the leftmost node of the right subtree and push nodes onto the stack
                while (node != null) {
                    stack.push(node);
                    node = node.getLeft();
                }
            }

            return value;
        }
    }

    private static class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }
    }
}