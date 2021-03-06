package org.opencb.datastore.core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by imedina on 20/03/14.
 */
public class QueryOptions extends ObjectMap {


    public QueryOptions() {

    }

    public QueryOptions(int size) {
        super(size);
    }

    public QueryOptions(final String key, final Object value) {
        super(key, value);
    }

    public QueryOptions(final Map<String, Object> inputOptions) {
        super(inputOptions);
    }

    public QueryOptions(final Map<String, ?> inputOptions, boolean pickFirstValue) {
        if (pickFirstValue) {
            for (Map.Entry<String, ?> option : inputOptions.entrySet()) {
                if (option.getValue() instanceof List) {
                    this.put(option.getKey(), ((List) option.getValue()).get(0));
                }
            }
        } else {
            this.putAll(inputOptions);
        }
    }

    public QueryOptions(String json) {
        super(json);
    }


    /**
     * This method safely add new options. If the key already exists it does not overwrite the current value.
     * You can use put for overwritten the value.
     *
     * @param key
     * @param value
     * @return null if the key was not present, or the existing object if the key exists.
     */
    public Object add(String key, Object value) {
        if (!this.containsKey(key)) {
            this.put(key, value);
            return null;
        }
        return this.get(key);
    }

    /**
     * This method safely add a new Object to an exiting option which type is List.
     *
     * @param key
     * @param value
     * @return the list with the new Object inserted.
     */
    public Object addToListOption(String key, Object value) {
        if (key != null && !key.equals("")) {
            if (this.containsKey(key) && this.getList(key) != null) {
                this.getList(key).add(value);
            } else {
                this.put(key, Arrays.asList(value));
            }
            return this.getList(key);
        }
        return null;
    }
}
