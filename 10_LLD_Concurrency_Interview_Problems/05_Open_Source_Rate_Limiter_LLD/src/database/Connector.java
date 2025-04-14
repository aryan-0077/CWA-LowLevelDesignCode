package database;

import java.util.ArrayList;
import java.util.List;

public class Connector {
    public void updateDB(String sql){

    }

    public <T> List<T> readDB(String sql, Class<T> clazz){
        return new ArrayList<>();
    }
}
