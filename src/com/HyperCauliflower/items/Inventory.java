package com.HyperCauliflower.items;

import com.HyperCauliflower.items.armor.Armor;
import com.HyperCauliflower.items.armor.HeadGear;
import com.sun.corba.se.spi.ior.IORTemplate;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by Henry on 21/07/2016.
 */
public class Inventory {

    private Armor currentHeadgear, currentChest, currentTrinketLeft, getCurrentTrinketRight;
    private ArrayList<Item> items;
    private final int MAX_INV_SIZE = 100;


    public Inventory(File file) {
        //todo get saved inventory from files for saved games

    }

    public Inventory(int maxInvSize) {
        //generate new inventory
        items = new ArrayList<>();
    }


    public void addItemToInventory(Item item) {
        if (items.size() == MAX_INV_SIZE) {
            //error for not having enough space
        } else {
            items.add(item);
        }
    }

    private void setHead(Armor a) {
        if (a instanceof HeadGear) {
            items.add(currentHeadgear);
            currentHeadgear = a;
        }
    }
}
