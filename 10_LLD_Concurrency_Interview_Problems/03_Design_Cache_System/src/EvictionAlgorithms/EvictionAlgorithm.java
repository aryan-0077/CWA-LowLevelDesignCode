package EvictionAlgorithms;

public interface EvictionAlgorithm<Key> {
    void keyAccessed(Key key) throws Exception;
    Key evictKey() throws Exception;
}
