public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        // Insert values into the binary tree
        tree.insert(50);
        tree.insert(17);
        tree.insert(23);
        tree.insert(12);
        tree.insert(9);
        tree.insert(14);
        tree.insert(19);
        tree.insert(72);
        tree.insert(54);
        tree.insert(76);
        tree.insert(67);

        // Print the tree using inorder traversal
        System.out.println("Inorder Traversal:");
        tree.inorderTraverse(new PrintVisitor<>());

        // Print the tree using preorder traversal
        System.out.println("\nPreorder Traversal:");
        tree.preorderTraverse(new PrintVisitor<>());

        // Print the tree using postorder traversal
        System.out.println("\nPostorder Traversal:");
        tree.postorderTraverse(new PrintVisitor<>());

        // Test the getSize() method
        int size = tree.getSize();
        System.out.println("\nSize of the tree: " + size);

        // Test the Inorder Iterator
        System.out.println("\nInorder Iterator:");
        for (Integer value : tree) {
            System.out.print(value + " ");
        }
    }

    // Visitor class for printing the elements of the tree
    private static class PrintVisitor<T> implements Visitor<T> {
        @Override
        public void visit(T value) {
            System.out.print(value + " ");
        }
    }
}
