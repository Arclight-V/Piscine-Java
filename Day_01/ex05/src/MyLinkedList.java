public class MyLinkedList<T> {
    T value_type;
    MyLinkedList<T>  next;
    MyLinkedList<T>  previous;

    public MyLinkedList(T value_type) {
        this.value_type = value_type;
        this.next = null;
        this.previous = null;
    }

    public MyLinkedList(T value_type, MyLinkedList<T> next, MyLinkedList<T> previous) {
        this.value_type = value_type;
        this.next = next;
        this.previous = previous;
    }

    public T getValue_type() {
        return value_type;
    }
}

