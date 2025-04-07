package UtilityClasses;


public class DoublyLinkedListNode<K> {
    DoublyLinkedListNode<K> next;
    DoublyLinkedListNode<K> prev;
    K dllElement;

    public DoublyLinkedListNode(K dllElement) {
        this.dllElement = dllElement;
        this.next = null;
        this.prev = null;
    }

    public DoublyLinkedListNode<K> getNextNode(){
        return next;
    }

    public DoublyLinkedListNode<K> getPreviousNode(){
        return prev;
    }

    public K getDLLElement(){
        return dllElement;
    }
}