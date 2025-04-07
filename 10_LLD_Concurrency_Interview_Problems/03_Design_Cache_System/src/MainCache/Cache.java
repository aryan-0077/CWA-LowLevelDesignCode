package MainCache;

import DataSources.DataStorage;
import EvictionAlgorithms.EvictionAlgorithm;
import java.util.concurrent.CompletableFuture;

public class Cache<Key, Value> {
    private final EvictionAlgorithm<Key> evictionAlgorithm;
    private final DataStorage<Key, Value> dataStorage;
    private static final Integer THRESHOLD_SIZE = 500;

    public Cache(EvictionAlgorithm<Key> evictionAlgorithm, DataStorage<Key, Value> dataStorage) {
        this.evictionAlgorithm = evictionAlgorithm;
        this.dataStorage = dataStorage;
    }

    public CompletableFuture<Value> get(Key key) throws Exception {
        try {
            if(dataStorage.containsKey(key)) {
                this.evictionAlgorithm.keyAccessed(key);
                return CompletableFuture.completedFuture(dataStorage.get(key));
            }
        } catch (Exception exception) {
            System.out.println("Tried to access non-existing key.");
            return null;
        }
        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<Void> put(Key key, Value value) throws Exception {
        try {
            if(dataStorage.containsKey(key)) {
                // Updation case
                return dataStorage.add(key, value)
                        .thenAccept(v -> {
                            try {
                                this.evictionAlgorithm.keyAccessed(key);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
            } else {
                // creation case
                if(dataStorage.getCapacity() >= THRESHOLD_SIZE) {
                    Key keyToEvict = this.evictionAlgorithm.evictKey();
                    if(keyToEvict != null) {
                        return dataStorage.remove(keyToEvict)
                                .thenCompose(v -> {
                                    try {
                                        return dataStorage.add(key, value);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                                .thenAccept(removeOperationResult -> {
                                    try {
                                        this.evictionAlgorithm.keyAccessed(key);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                    }
                }
                return dataStorage.add(key, value)
                        .thenAccept(addOperationResult -> {
                            try {
                                this.evictionAlgorithm.keyAccessed(key);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
        } catch (Exception exception) {
            System.out.println("Error occurred while putting key-value pair: " + exception.getMessage());
            return CompletableFuture.failedFuture(exception);
        }
    }
}