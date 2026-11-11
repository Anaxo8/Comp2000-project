package Entities;
import Items.Item;

public class Enemy extends Entity {
    Item loot; // single set Item as loot;

    public Enemy(String name, int hp, int atk, int def, Item loot){
        super(name, hp, atk, def);

        this.loot = loot;
    }

    public void setLoot(Item loot){
        this.loot = loot;
    }

    // TODO: should drop its items on death

    public Item dropLoot(){ // simply returns its inventory. Run this when the Enemy is died.
        Item temp = this.loot;
        this.loot = null;

        return temp;
    }

    public String toString(){
        return String.format("%s, %s, %s", this.name, this.stats.toString(), this.loot.getName());
    }
}
