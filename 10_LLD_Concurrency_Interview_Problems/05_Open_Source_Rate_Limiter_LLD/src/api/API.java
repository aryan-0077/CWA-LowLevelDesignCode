package api;

import javax.transaction.xa.XAException;

public interface API {
    void updateKey(String json);

    void updateServiceConfig(String json);

    void readKey(String json);

    void readServiceConfig(String json);
}
