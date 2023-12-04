import java.util.NoSuchElementException;

class LinkedList<T> {

    private static class Node<T> {
        private Node<T> next;
        private T value;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public void addLast(T item) {

        var node = new Node<>(item);
        if (isEmpty())
            first = last = node;
        else {
            last.next = node;
            last = node;
        }

        size++;

    }

    public void addFirst(T item) {
        var node = new Node<>(item);
        if (isEmpty())
            first = last = node;
        else {
            node.next = first;
            first = node;
        }
        size++;
    }


    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (first == last)
            first = last = null;
        else {
            var node = first.next;
            first.next = null;
            first = node;
        }
        size--;
    }

    public void removeLast() {

        if (isEmpty())
            throw new NullPointerException();
        if (first == last)
            first = last = null;
        else {
            var previous = getPrevious(last);
            last = previous;
            last.next = null;
        }
        size--;
    }

    private Node<T> getPrevious(Node<T> node) {
        var current = first;
        while (current != null) {
            if (current.next == last) return current;
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public T getLast() {
        return last.value;
    }

    public boolean isEmpty() {
        return first == null;
    }


    public void printList() {
        var current = first;
        if (isEmpty()) {
            throw new NullPointerException();
        } else {
            while (current != null) {
                System.out.print(current.value);
                current = current.next;
            }
        }
    }

}

class Stack<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T item) {
        list.addLast(item);
    }

    public T peek() {
        return list.getLast();
    }

    public T pop() {
        T top = list.getLast();
        list.removeLast();
        return top;
    }

    public boolean empty() {
        return list.size() == 0;
    }


    public boolean isEmpty(){return list.isEmpty();}
    public void print() {
        list.printList();
    }}

