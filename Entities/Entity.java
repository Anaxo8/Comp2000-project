public abstract class Entity {
    private Stats stats;
    private String name;
    private Inventory inv;

    Entity(String name, int hp, int atk, int def) {
        this.stats = new Stats(hp, atk, def);
        this.inv = new Inventory();
    } 

    Entity(String name, int hp, int atk, int def, Inventory inv) {
        this.name = name;
        
        this.inv = inv;
    }

    public void addItem(Item toAdd){
        inv.addItem(toAdd);
    }
    
    public void attack(Entity other) {
        if (other == null)
            return;

        other.setHp(other.getHp() - this.getAtk() + other.getDef()); // simple algorithm, dmg = this atk - other def. subtract dmg from other.hp
    }

    private void itemStatUps(){ // access the items to calculate how they affect the stats
        
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
