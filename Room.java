import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import Entities.Enemy;
import Items.*;

enum RoomType {
    EMPTY,
    ITEM,
    MONSTER
}
enum RoomRole{ //Saw enum in item class and thought it'd make sense here
    START,
    NORMAL,
    END
}
public class Room{
    private int id;
    private RoomType type;
    private RoomRole role;
    public List<Enemy> contents;
    // can keep this, use Casting?  actually idk

    Room(int id, RoomRole role) {
        this.id = id;
        this.role = role;
        this.type = RoomType.EMPTY;
        this.contents = new ArrayList<Enemy>();
    }

    public void rollRoomType() {
        Random n = new Random();
        int roll = n.nextInt(51);
        
        if (role == RoomRole.START) {
            type = RoomType.EMPTY;
            return;
        }

        if (role == RoomRole.END)  {
            type = RoomType.ITEM;
            return;
        }

        if (role == RoomRole.NORMAL && roll == -1) { //formerly if even, now impossible
            type = RoomType.ITEM;
            //IDEA: Add a specific number of random items from a list (TBD if this will happen)
            
        } else {
            type = RoomType.MONSTER;
            //SAME IDEA AS THE ITEM BUT WITH MONSTERS

            roll = n.nextInt(101);
            String difficulty = null;

            if (roll <= 70) { // 0 - 45
                difficulty = "easy";
            }
            else if (roll <= 90) { // 46 - 80
                difficulty = "med";
            }
            else { // 81 - 100
                difficulty = "hard";
            }

            int enemyCount = n.nextInt(4) + 1; // 1 - 4 enemies

            this.contents.addAll(generateEnemies(enemyCount, difficulty)); 
        }
    }

    private List<String> generateEnemyPool(String difficulty){
        try (BufferedReader fileReader = Files.newBufferedReader(Path.of("enemies.csv"))) {
            return fileReader.lines()
                             .skip(1) // first is header
                             .filter(line -> {
                                String[] split = line.split(","); // split by delimiter

                                return split[4].equals(difficulty); // filter by the difficulty (stored at 4th index/5th column of csv)
                             })
                             .collect(Collectors.toList());
        
        } catch (FileNotFoundException e) {
            System.out.println("file no found");
            return null; // get empty list if fuck up

        } catch (NumberFormatException e) {
            System.out.println("Check formatting of .csv file");
            return null;

        } catch (Exception e) {
            System.out.println("huh/??");
            return null;
        }
    }

    private List<String> generateItemPool(int scoreMin, int scoreMax){ //items the enemies can spawn with
        try (BufferedReader fileReader = Files.newBufferedReader(Path.of("items.csv"))) {
            return fileReader.lines()
                             .skip(1) // first is header
                             .filter(line -> {
                                String[] split = line.split(","); // split by delimiter

                                int score = Integer.parseInt(split[2]);
                                return score >= scoreMin && score <= scoreMax; // filter by the difficulty (stored at 4th index/5th column of csv)
                             })
                             .collect(Collectors.toList());
        
        } catch (FileNotFoundException e) {
            System.out.println("file no found");
            return null; // get empty list if fuck up

        } catch (NumberFormatException e) {
            System.out.println("Check formatting of .csv file");
            return null;

        } catch (Exception e) {
            System.out.println("huh/??");
            return null;
        }
    }


    List<Enemy> generateEnemies(int count, String difficulty){
        List<String> enemyPool = generateEnemyPool(difficulty);
        ArrayList<Enemy> chosenEnemies = new ArrayList<Enemy>();

        if (enemyPool == null || enemyPool.isEmpty()){
            System.out.println("No available enemies to choose");
            return null;
        }

        Random r = new Random();
        for (int i = 0; i < count; i++){
            String line = enemyPool.get(r.nextInt(enemyPool.size()));

            String[] split = line.split(",");

            chosenEnemies.add(new Enemy(
                split[0], 
                Integer.parseInt(split[1]), 
                Integer.parseInt(split[2]), 
                Integer.parseInt(split[3]), 
                null));
        }

        try {
            giveEnemiesLoot(chosenEnemies, difficulty);    
        } catch (BadDifficultyException e) {
            System.out.println("The enemies do not have loot...");
        }
        
        return chosenEnemies;
    }

    private void giveEnemiesLoot(List<Enemy> enemies, String difficulty) throws BadDifficultyException {
        int scoreMin = 0;
        int scoreMax = 0;

        switch (difficulty) {
            case "easy":
                scoreMin = 0;
                scoreMax = 20;
                break;
            case "med":
                scoreMin = 15;
                scoreMax = 30;
                break;
            case "hard":
                scoreMin = 20;
                scoreMax = 99;
                break;
            default:
                throw new BadDifficultyException();
                
        }

        List<String> itemPool = generateItemPool(scoreMin, scoreMax);

        Random r = new Random();

        for (Enemy e : enemies) {
            Item loot = null;
            String line = itemPool.get(r.nextInt(itemPool.size()));
            String[] split = line.split(",");

            if (split[0].equals("weapon")){
                loot = new Weapon(split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
            }
            else {
                loot = new Armour(split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
            }

            e.setLoot(loot);
        }
    }

    /* 
    public void addContent(Object content) {
        if (content != null) {
            contents.add(content);
        }
    }
    */

    public void clearContents() {
        contents.clear();
    }
}