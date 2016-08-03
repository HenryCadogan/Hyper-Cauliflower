package com.HyperCauliflower.items;

import com.HyperCauliflower.items.armor.Armor;
import com.HyperCauliflower.items.armor.BreastPlate;
import com.HyperCauliflower.items.armor.HeadGear;
import com.HyperCauliflower.items.armor.LegArmor;

/**
 * Created by Tim on 04/08/2016.
 */
public class InventoryA {

    private Armor[] equippedArmor = new Armor[3];
    private Item[] hotbar = new Item[10];
    private Item[] storedItems = new Item[50];

    public Item equipArmor(Armor armor) {
        Armor oldItem = null;
        if (armor instanceof HeadGear) {
            oldItem = equippedArmor[0];
            equippedArmor[0] = armor;
        } else if (armor instanceof BreastPlate) {
            oldItem = equippedArmor[1];
            equippedArmor[1] = armor;
        } else if (armor instanceof LegArmor) {
            oldItem = equippedArmor[2];
            equippedArmor[2] = armor;
        }
        return oldItem;
    }

    public Item addHotbar(int index, Item item) {
        if (index >= hotbar.length) {
            return item;
        }
        Item oldItem = hotbar[index];
        hotbar[index] = item;
        return oldItem;
    }

    public Item addItem(int index, Item item) {
        if (index >= storedItems.length) {
            return item;
        }
        Item oldItem = storedItems[index];
        storedItems[index] = item;
        return oldItem;
    }
}
