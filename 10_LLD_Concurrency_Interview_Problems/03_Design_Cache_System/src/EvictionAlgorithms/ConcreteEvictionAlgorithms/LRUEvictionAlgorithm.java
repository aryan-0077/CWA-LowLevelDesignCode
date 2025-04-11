package EvictionAlgorithms.ConcreteEvictionAlgorithms;

import EvictionAlgorithms.EvictionAlgorithm;
import UtilityClasses.DoublyLinkedList;
import UtilityClasses.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionAlgorithm<K> implements EvictionAlgorithm<K> {
    // A custom doubly linked list to track the LRU order.
    private final DoublyLinkedList<K> dll;
    // Map of key to its node in the linked list.
    private final Map<K, DoublyLinkedListNode<K>> keyToNodeMap;

    public LRUEvictionAlgorithm() {
        this.dll = new DoublyLinkedList<>();
        this.keyToNodeMap = new HashMap<>();
    }

    @Override
    public synchronized void keyAccessed(K key) throws Exception {
        if (keyToNodeMap.containsKey(key)) {
            // Move the node to the tail (most recently used).
            DoublyLinkedListNode<K> node = keyToNodeMap.get(key);
            dll.detachNode(node);
            dll.addNodeAtTail(node);
        } else {
            // New key: add it to the tail.
            DoublyLinkedListNode<K> newNode = new DoublyLinkedListNode<>(key);
            dll.addNodeAtTail(newNode);
            keyToNodeMap.put(key, newNode);
        }
    }

    @Override
    public synchronized K evictKey() throws Exception {
        // Evict the least recently used key (from the head).
        DoublyLinkedListNode<K> nodeToEvict = dll.getHead();
        if (nodeToEvict == null) {
            return null;
        }
        K evictKey = nodeToEvict.getValue();
        dll.removeHead();
        keyToNodeMap.remove(evictKey);
        return evictKey;
    }
}

