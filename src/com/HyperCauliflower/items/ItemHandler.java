package com.HyperCauliflower.items;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Matt on 27/08/2016.
 */
public class ItemHandler {

    private enum WeaponType{
        RANGED_STANDARD
    }
    private final String PATH = "res/json/items.json";
    private HashMap<String, JSONObject> weapons;
    private HashMap<String, JSONObject> armors;
    private HashMap<String, ProjectileFactory> projectiles;
    public ItemHandler(SpriteSheetData projectileSprites){
        try {
            weapons = new HashMap<>();
            projectiles = new HashMap<>();
            armors = new HashMap<>();
            JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader(PATH));
            for(Object weapon:(JSONArray)obj.get("weapons")){
                JSONObject w = (JSONObject) weapon;
                weapons.put((String)w.get("name"),w);
            }
            for(Object armor:(JSONArray)obj.get("armor")){
                JSONObject a = (JSONObject) armor;
                armors.put((String)a.get("name"),a);
            }
            for(Object projectile:(JSONArray)obj.get("projectiles")){
                JSONObject p = (JSONObject) projectile;
                projectiles.put((String)p.get("name"),
                        new ProjectileFactory(((Number)p.get("speed")).intValue(),
                                projectileSprites.getImage((String)p.get("name"),0)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public Armor generateArmor(String name, GameState game){
        JSONObject armor = armors.get(name);
        return new Armor(0,0,
                name,
                (String)armor.get("slot"));
    }

    public Weapon generateWeapon(String name, GameState game){
        JSONObject weapon = weapons.get(name);
        switch(WeaponType.valueOf((String)weapon.get("type"))){
            case RANGED_STANDARD:
                return new RangedStandardWeapon(0,0,
                        name,
                        ((Number)weapon.get("firerate")).intValue(),
                        game,
                        projectiles.get(weapon.get("projectile")));
            default:
                return null;
        }
    }





}
