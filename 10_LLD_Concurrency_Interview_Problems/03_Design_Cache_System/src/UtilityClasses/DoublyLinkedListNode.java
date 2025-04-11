package UtilityClasses;


public class DoublyLinkedListNode<K> {
    private final K value;
    DoublyLinkedListNode<K> prev;
    DoublyLinkedListNode<K> next;

    public DoublyLinkedListNode(K value) {
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public K getValue() {
        return value;
    }
}
