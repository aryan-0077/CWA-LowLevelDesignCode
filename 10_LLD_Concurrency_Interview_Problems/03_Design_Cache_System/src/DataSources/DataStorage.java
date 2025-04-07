package DataSources;

import java.util.concurrent.CompletableFuture;

public interface DataStorage<Key, Value> {
    CompletableFuture<Void> add(Key key, Value value) throws Exception;
    CompletableFuture<Void> remove(Key key) throws Exception;
    Value get(Key key) throws Exception;
    boolean containsKey(Key key) throws Exception;
    Integer getCapacity();
}