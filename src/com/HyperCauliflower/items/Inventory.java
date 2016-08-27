package com.HyperCauliflower.items;

import com.HyperCauliflower.abilities.Ability;

/**
 * Created by Tim on 04/08/2016.
 */
public class Inventory {

    private Armor[] equippedArmor = new Armor[3];
    private Weapon equippedWeapon;
    private Item[] storedItems = new Item[50];
    private Ability abilityBar[] = new Ability[4];

    public Item equipArmor(Armor armor) {
        //todo make work with the new system
        return null;
    }

    public void equipWeapon(Weapon w){
        //return current weapon to backpack
        if (equippedWeapon!= null) {
            this.storedItems[storedItems.length + 1] = this.equippedWeapon;
        }
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
