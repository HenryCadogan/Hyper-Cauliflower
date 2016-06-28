package com.HyperCauliflower.handlers;

import org.json.simple.JSONObject;

/**
 * Created by Matt on 28/06/2016.
 */
public interface Loadable<T> {
    public T load(JSONObject j);
}
