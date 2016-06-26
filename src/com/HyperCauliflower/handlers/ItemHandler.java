package com.HyperCauliflower.handlers;



import com.HyperCauliflower.items.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Henry on 26/06/2016.
 */
public class ItemHandler {
    private HashMap items = new HashMap<String, Item>();

    public ItemHandler(){
        this.items.putAll(loadItems());
    }

    public Map loadItems(){
        //load com.HyperCauliflower.items from JSON Handler
        //load all com.HyperCauliflower.items in to List<> then add them into Items
        return null;
    }

    public Object getItem(String key){
        if (items.keySet().contains(key)){
            return items.get(key);
        }else {
            System.exit(2);
            System.out.println("No such item in hashmap Items");
        }
        //never reaches this state
        return null;
    }

}
