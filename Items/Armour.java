package Items;

import Stats.Stats;

public class Armour extends Item {
    public enum Position {
        HELM,
        CHEST,
        BOOTS,
        SHIELD
    }  

    private Position position;

    /**
     * Constructor for Armour objects
     * @param name Name of item as string
     * @param score Gear score
     * @param def Value that armour buffs defence stat by
     * @param position 0 = Helmet, 1 = Chestplate, 2 = Boots, 3 = Shield. Modulo 4 applies to values greater than 3.
     */
    public Armour(String name, int score, int def, int position){
        super(name, score);

        setStatUp(def);
        
        if (position % 4 == 0){
            this.position = Position.HELM;
        }
        else if (position % 4 == 1){
            this.position = Position.CHEST;
        }
        else if (position % 4 == 2){
            this.position = Position.BOOTS;
        }
        else if (position % 4 == 3){
            this.position = Position.SHIELD;
        }
    }

    @Override
    public void setStatUp(int def){
        this.statUp = new Stats(0, 0, def);
    }
    public Position getPosition(){
        return this.position;
    }
}
