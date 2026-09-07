package Stats;
public class Stats {
    // class for dealing with and adding-subtracting stats

    private int hp;
    private int hpMax;
    private int atk;
    private int def;

    public Stats(){
        this.hp = 0;
        this.hpMax = 0;
        this.atk = 0;
        this.def = 0;
    }

    public Stats(int hp, int atk, int def){
        this.hp = hp;
        this.hpMax = hp;
        this.atk = atk;
        this.def = def;
    }

    public void statsAdd(Stats other){ // adds the value of other's stats to this objects stats
        if (other == null)
            return;

        this.hpMax += other.hpMax;
        this.atk += other.atk;
        this.def += other.def;
    }

    public void statsMinus(Stats other) {
        if (other == null)
            return;

        this.hpMax -= other.hpMax;
        this.atk -= other.atk;
        this.def -= other.def;
    }

    @Override
    public String toString(){
        String s = this.hp + "/" + this.hpMax + " " + this.atk + " " + this.def;

        return s;
    }

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
}