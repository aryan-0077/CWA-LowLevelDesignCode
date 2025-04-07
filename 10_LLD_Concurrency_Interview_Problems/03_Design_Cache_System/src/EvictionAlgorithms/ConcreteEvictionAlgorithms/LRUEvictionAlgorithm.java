package EvictionAlgorithms.ConcreteEvictionAlgorithms;

import EvictionAlgorithms.EvictionAlgorithm;
import UtilityClasses.DoublyLinkedList;
import UtilityClasses.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionAlgorithm <Key> implements EvictionAlgorithm<Key> {

    private DoublyLinkedList<Key> doublyLinkedList;
    private Map<Key, DoublyLinkedListNode<Key>> map;

    public LRUEvictionAlgorithm() {
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.map = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) throws Exception {
        DoublyLinkedListNode<Key> node = map.get(key);
        if(map.containsKey(key)){
          doublyLinkedList.detachNode(node);
          doublyLinkedList.addNodeAtLast(node);
        }else{
            DoublyLinkedListNode<Key> newNode = doublyLinkedList.addElementAtLast(key);
           map.put(key , node);
        }
    }

    @Override
    public Key evictKey() throws Exception {
        DoublyLinkedListNode<Key> first = doublyLinkedList.getFirstNode();
        if(first == null) {
            return null;
        }
        doublyLinkedList.detachNode(first);
        return first.getDLLElement();
    }
}
