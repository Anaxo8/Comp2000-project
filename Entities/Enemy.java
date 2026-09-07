package Entities;
import Items.Item;

public class Enemy extends Entity {
    Item loot; // single set Item as loot;

    public Enemy(String name, int hp, int atk, int def, Item loot){
        super(name, hp, atk, def);

        this.loot = loot;
    }

    // TODO: should drop its items on death

    public Item dropLoot(){ // simply returns its inventory. Run this when the Enemy is died.
        return this.loot;
    }
}
