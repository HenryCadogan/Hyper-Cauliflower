package com.HyperCauliflower.items;

import com.HyperCauliflower.handlers.SpriteSheetData;
import com.HyperCauliflower.states.GameState;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.lwjgl.Sys;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Matt on 27/08/2016.
 */
public class WeaponHandler {
    private final String PATH = "res/json/items.json";
    private HashMap<String, JSONObject> weapons;
    private HashMap<String, ProjectileFactory> projectiles;
    public WeaponHandler(SpriteSheetData projectileSprites){
        try {
            weapons = new HashMap<>();
            projectiles = new HashMap<>();
            JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader(PATH));
            for(Object weapon:(JSONArray)obj.get("weapons")){
                JSONObject w = (JSONObject) weapon;
                weapons.put((String)w.get("name"),w);
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


    public Weapon generateWeapon(String name, GameState game){
        JSONObject weapon = weapons.get(name);
        System.out.println((String)weapon.get("type"));
        if(((String)weapon.get("type")).equals("ranged standard"))
            return new RangedStandardWeapon(0,0,(String)weapon.get("name"),((Number)weapon.get("firerate")).intValue(), game, projectiles.get((String)weapon.get("projectile")));
        else
            return null;
    }
}
