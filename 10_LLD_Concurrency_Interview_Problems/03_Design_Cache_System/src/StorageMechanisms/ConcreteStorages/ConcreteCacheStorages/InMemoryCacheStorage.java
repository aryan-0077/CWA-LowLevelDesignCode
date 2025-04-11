package StorageMechanisms.ConcreteStorages.ConcreteCacheStorages;
import StorageMechanisms.Interfaces.CacheStorage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class InMemoryCacheStorage<K, V> implements CacheStorage<K, V> {
    private final Map<K, V> cache;
    private final int capacity;

    public InMemoryCacheStorage(int capacity) {
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public void put(K key, V value) throws Exception {
        cache.put(key, value);
    }

    @Override
    public V get(K key) throws Exception {
        if (!cache.containsKey(key)) {
            throw new Exception("Key not in cache: " + key);
        }
        return cache.get(key);
    }

    @Override
    public void remove(K key) throws Exception {
        if (!cache.containsKey(key)) {
            throw new Exception("Key not in cache: " + key);
        }
        cache.remove(key);
    }

    @Override
    public boolean containsKey(K key) throws Exception {
        return cache.containsKey(key);
    }

    @Override
    public int size() throws Exception {
        return cache.size();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
