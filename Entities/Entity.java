package Entities;
import Items.*;
import Stats.Stats;

public abstract class Entity {
    protected boolean isDied;
    protected Stats stats;
    protected String name;
    protected Inventory inv;
    
    private Stats statUps;

    Entity(String name, int hp, int atk, int def) {
        this.stats = new Stats(hp, atk, def);
        this.isDied = false;
        this.inv = new Inventory();
    } 

    Entity(String name, int hp, int atk, int def, Inventory inv) {
        this.name = name;
        this.stats = new Stats(hp, atk, def);
        this.isDied = false;
        this.inv = inv;
    }

    public void addWeapon(Weapon item){
        this.inv.addWeapon(item);
    }

    public void addArmour(Armour item){
        this.inv.addArmour(item);
    }
    
    public void attack(Entity other) {
        if (other == null)
            return;

        int dmg = this.getAtk() - other.getDef();
        if (dmg <= 0)
            dmg = 1; // minimum of 1 dmg to prevent infinite battles;

        other.setHp(other.getHp() - dmg); // simple algorithm, dmg = this atk - other def. subtract dmg from other.hp
    }

    public void getStatUps(){ 
        this.statUps = this.inv.getTotalStatUps(); // Entity now knows the stat total of its inventory
    }

    public void applyStatUps(){
        this.stats.statsAdd(statUps); 
    }

    public void removeStatUps(){
        this.stats.statsMinus(statUps);
    }

    public boolean isDied(){
        if (this.getHp() <= 0){
            isDied = true;
        }
        else {
            isDied = false;
        }

        return isDied;
    }


    // getters and setters
    public int getHp() {
        return this.stats.getHp();
    }

    public void setHp(int hp) {
        this.stats.setHp(hp);
    }

    public int getHpMax() {
        return this.stats.getHpMax();
    }

    public void setHpMax(int hpMax) {
        this.stats.setHpMax(hpMax);
    }

    public int getAtk() {
        return this.stats.getAtk();
    }

    public void setAtk(int atk) {
        this.stats.setAtk(atk);
    }

    public int getDef() {
        return this.stats.getDef();
    }

    public void setDef(int def) {
        this.stats.setDef(def);
    }

    public Stats getStats(){
        return this.stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    } 
}
