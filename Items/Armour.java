package Items;

import Stats.Stats;

public class Armour extends Item {
    public Armour(String name, int score, int def){
        super(name, score);

        setStatUp(def);
    }

    @Override
    public void setStatUp(int def){
        this.statUp = new Stats(0, 0, def);
    }
}
