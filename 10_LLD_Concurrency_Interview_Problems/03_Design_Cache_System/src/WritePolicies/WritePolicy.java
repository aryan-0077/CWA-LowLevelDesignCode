package WritePolicies;

import StorageMechanisms.Interfaces.CacheStorage;
import StorageMechanisms.Interfaces.DBStorage;

public interface WritePolicy<K, V> {
    /**
     * Write a key/value pair to both cache storage and DB storage concurrently.
     * This is the write‑through policy.
     */
    void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception;
}
