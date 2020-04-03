package eg.edu.alexu.csd.datastructure.stack.cs23.Classes;


import eg.edu.alexu.csd.datastructure.stack.cs23.Interfaces.IStack;

public class Stack implements IStack {
    SinglyLinkedList arr;

    Stack(){
       arr = new SinglyLinkedList();
    }

    public Object pop() {
        if(this.isEmpty()) throw new RuntimeException("Stack is empty, you can't pop");
        Object elm = this.peek();
        arr.remove( arr.size() - 1 );
        return elm;
    }

    public Object peek() {
        if(this.isEmpty()) throw new RuntimeException("Stack is empty, you can't peek");
        return arr.get( arr.size()-1 );
    }

    public void push(Object element) {
        arr.add(element);
        return;
    }

    public boolean isEmpty() {
        return (arr.size() == 0);
    }

    public int size() {
        return arr.size();
    }
}
