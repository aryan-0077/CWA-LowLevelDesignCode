package DataSources;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

// Assume this is some DAO layer
public class HashMapDataSource<Key, Value> implements DataStorage<Key, Value> {
    Map<Key, Value> dataStore;
    private final Integer capacity;

    public HashMapDataSource(Integer capacity) {
        this.capacity = capacity;
        dataStore = new ConcurrentHashMap<>(); // since many threads will be trying to access this map
    }

    @Override
    public CompletableFuture<Void> add(Key key, Value value) throws Exception {
        if(isReachedThreshold()) throw new Exception("Storage is Full");

        dataStore.put(key, value);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> remove(Key key) throws Exception {
        if(!dataStore.containsKey(key)) throw new Exception("No value present in the data store for the key : " + key);

        dataStore.remove(key);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public Value get(Key key) throws Exception {
        if(!dataStore.containsKey(key))
            throw new Exception("No value present in the data store for the key : " + key);

        return dataStore.get(key);
    }

    @Override
    public boolean containsKey(Key key) throws Exception {
        return dataStore.containsKey(key);
    }

    @Override
    public Integer getCapacity() {
        return capacity;
    }

    private boolean isReachedThreshold() {
        return dataStore.size() == capacity;
    }

    // Getters Section Start
    public Map<Key, Value> getDataStore() {
        return dataStore;
    }
}