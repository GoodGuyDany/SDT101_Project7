class PrintVisitor<T> implements Visitor<T> { // Creating a PrintVisitor class that implements the visit method
    @Override
    public void visit(T value) { // Implementing the visit method
        System.out.print(value + " ");
    }
}