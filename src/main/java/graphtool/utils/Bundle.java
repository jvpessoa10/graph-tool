package graphtool.utils;

import java.util.HashMap;

public class Bundle <T> {

    private final HashMap<String, T> data;

    public Bundle() {
        data = new HashMap<>();
    }

    public void put(String name, T value) {
        data.put(name, value);
    }

    public T get(String name) {
        return data.get(name);
    }
}
