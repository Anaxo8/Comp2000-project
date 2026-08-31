public class Item {
    private String name;
    private int score;
    private Rarity rarity;

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
    }

    Item() { 
        this.name = null;
        this.score = 0;
        this.rarity = null;
    }

    Item compare(Item other) { // compares scores, returns the one with higher score. if they are equal, returns the calling object
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
}
