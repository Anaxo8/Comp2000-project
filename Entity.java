public class Entity {
    int hp;
    int hpMax;
    int atk;
    int def;

    String name;
    Inventory inv;

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
}
