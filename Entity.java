public class Entity {
    private int hp;
    private int hpMax;
    private int atk;
    private int def;

    private String name;
    private Inventory inv;

    Entity(String name, int hp, int atk, int def) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = atk;
        this.inv = new Inventory();
    } 

    Entity(String name, int hp, int atk, int def, Inventory inv) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = atk;
        this.inv = inv;
    }

    public void addItem(Item toAdd){
        inv.addItem(toAdd);
    }
    
    public void attack(Entity other) {
        if (other == null)
            return;

        other.setHp(other.getHp() - this.atk + other.getDef()); // simple algorithm, dmg = this atk - other def. subtract dmg from other.hp
    }

    private void itemStatUps(){ // access the items to calculate how they affect the stats

    }

    // getters and setters
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
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
