package EvictionAlgorithms.ConcreteEvictionAlgorithms;

import EvictionAlgorithms.EvictionAlgorithm;
import UtilityClasses.DoublyLinkedList;
import UtilityClasses.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUEvictionAlgorithm<Key> implements EvictionAlgorithm<Key> {

private Map<Key, Integer> frequencyMap;
private Map<Key, DoublyLinkedListNode<Key>> positionMap;
private DoublyLinkedList<Key> frequencyList; // Stores keys grouped by frequency

public LFUEvictionAlgorithm() {
        this.frequencyMap = new HashMap<>();
        this.positionMap = new HashMap<>();
        this.frequencyList = new DoublyLinkedList<>();
        }

@Override
public void keyAccessed(Key key) throws Exception {
        if (frequencyMap.containsKey(key)) {
        int currentFrequency = frequencyMap.get(key);
        frequencyMap.put(key, currentFrequency + 1);
        } else {
        frequencyMap.put(key, 1);
        DoublyLinkedListNode<Key> newNode = frequencyList.addElementAtLast(key);
        positionMap.put(key, newNode);
        }
        }

@Override
public Key evictKey() throws Exception {
        DoublyLinkedListNode<Key> first = frequencyList.getFirstNode();
        if (first == null) {
        return null;
        }

        Key keyToEvict = first.getDLLElement();
        frequencyList.detachNode(first);
        frequencyMap.remove(keyToEvict);
        positionMap.remove(keyToEvict);
        return keyToEvict;
        }
        }