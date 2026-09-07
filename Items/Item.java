package Items;
import javax.swing.ImageIcon;
import Stats.Stats;

public abstract class Item {
    protected String name;
    protected int score;
    protected Rarity rarity;
    private String fileName;
    protected Stats statUp;

    enum Rarity { //enum got mentioned, so putting in as a start.
        COMMON,
        UNCOMMON,
        RARE,
        LEGENDARY
    }

    Item(String name, int score) { // set some shit manually
        this.name = name;
        this.score = score;

        if (this.score <= 25){
            this.rarity = Rarity.COMMON;
        }
        else if (this.score <= 50) {
            this.rarity = Rarity.UNCOMMON;
        }
        else if (this.score <= 75) {
            this.rarity = Rarity.RARE;
        }
        else {
            this.rarity = Rarity.LEGENDARY;
        }

        fileName = "./images/fallback.jpg";
    }

    Item() { 
        this.name = null;
        this.score = 0;
        this.rarity = null;
        this.statUp = null;
        this.fileName = "./images/fallback.jpg";
    }

    @Override
    public String toString(){
        String s = this.name + " " + this.score + " " + this.rarity + " " + this.statUp.toString();

        return s;
    }

    public Item compareBetter(Item other) { // compares scores, returns the one with higher score. if they are equal, returns the calling object
        Item better;

        if (this.getScore() < other.getScore()) {
            better = other;
        }
        // this has a greater or equal score to other
        else {
            better = this;
        }
        
        return better;
    }

    public Item compareWorse(Item other) { // compares scores, returns the one with lower score. if they are equal, returns the calling object
        Item worse;

        if (this.getScore() > other.getScore()) {
            worse = other;
        }
        // this has a lesser or equal score
        else {
            worse = this;
        }
        
        return worse;
    }

    public abstract void setStatUp(int stat); // each subclass can set a different stat


    public static ImageIcon loadImage(String URL)
    {
        if(URL != null)
        {
            System.out.println("Loaded image: "+URL);
            return new ImageIcon(URL);
        }
        else
        {
            System.out.println("Failed to load image!");
            return new ImageIcon("./fallback.jpg");
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public Stats getStatUp(){
        return this.statUp;
    }
}
