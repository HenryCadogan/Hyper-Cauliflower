package items;

import states.Renderable;

import java.util.Random;

/**
 * Created by Henry on 26/06/2016.
 */
public abstract class Item implements Renderable {
    private int value;
    private int rarity =-1;

    public Item(int rarityModifier,int value){
        this.rarity = generateRarity(rarityModifier);
    }

    public int generateRarity(int modifier){
        //rarity levels are [insert comedic sheep based rarity puns here]
        int rand = new Random().nextInt(60) + modifier;
        if (rand < 20){
            rarity = 0;
            //common
        }else if(rand >= 20 && rand < 35 ){
            rarity = 1;
            //uncommon
        }else if(rand >= 35 && rand < 47 ){
            rarity = 2;
            //rare
        }else if(rand >= 47 && rand < 57 ) {
            rarity = 3;
            //epic
        }else if(rand >= 57){
            rarity = 4;
            //Sheep

        }
            //Throw error for invalid rarity
        return rarity;
    }
}
