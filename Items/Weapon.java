package Items;

import Stats.Stats;

public class Weapon extends Item {
    
    public Weapon(String name, int score, int atk){
        super(name, score);

        setStatUp(atk);
    }

    @Override
    public void setStatUp(int atk){
        this.statUp = new Stats(0, atk, 0);
    }

}
