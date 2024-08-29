public class ListaDuplamenteEncadeada<T> {

    private Node<T> base;
    private Node<T> top;
    private int size;

    public ListaDuplamenteEncadeada() {
        this.base = null;
        this.top = null;
        this.size = 0;
    }

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return false;
    }

    public int getSize() {
        return size;
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            base = top = newNode;
        } else {
            top.next = newNode;
            newNode.prev = top;
            top = newNode;
        }
        size++;
    }

    public T remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        Node<T> current = getNode(pos);
        T data = current.data;

        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            base = current.next;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            top = current.prev;
        }

        size--;
        return data;
    }

    public T remove(Node<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Nó inválido");
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            base = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            top = node.prev;
        }

        size--;
        return node.data;
    }

    public Node<T> getNode(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        Node<T> current;
        if (pos < size / 2) {
            current = base;
            for (int i = 0; i < pos; i++) {
                current = current.next;
            }
        } else {
            current = top;
            for (int i = size - 1; i > pos; i--) {
                current = current.prev;
            }
        }

        return current;
    }

    public T get(int pos) {
        Node<T> node = getNode(pos);
        return node.data;
    }

    public void set(int pos, T value) {
        Node<T> node = getNode(pos);
        node.data = value;
    }
}
