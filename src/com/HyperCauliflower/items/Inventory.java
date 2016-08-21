package com.HyperCauliflower.items;

import com.HyperCauliflower.items.armor.Armor;
import com.HyperCauliflower.items.armor.BreastPlate;
import com.HyperCauliflower.items.armor.HeadGear;
import com.HyperCauliflower.items.armor.LegArmor;
import com.HyperCauliflower.items.weapons.Weapon;

/**
 * Created by Tim on 04/08/2016.
 */
public class Inventory {

    private Armor[] equippedArmor = new Armor[3];
    private Weapon equippedWeapon;
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

    public void equipWeapon(Weapon w){
        //return current weapon to backpack
        this.storedItems[storedItems.length+1] = this.equippedWeapon;
        //add current weapon to the equipped weapon variable
        this.equippedWeapon = w;
    }


    public Item addItem(int index, Item item) {
        if (index >= storedItems.length) {
            return item;
        }
        Item oldItem = storedItems[index];
        storedItems[index] = item;
        return oldItem;
    }

    public Weapon getEquippedWeapon(){
        return this.equippedWeapon;
    }

    public int getTotalArmorRating(){
        int total = 0;
        for (Armor a:equippedArmor){
            total += a.getArmorRating();
        }
        return total;
    }

}
