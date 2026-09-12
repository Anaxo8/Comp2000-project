import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import Entities.Entity;
import Entities.Player;
import Entities.Enemy;

public class CombatController {

    private List<Entity> turnOrder = new ArrayList<>();
    private int currentTurn = 0;
    private int currentRound = 0;

    public enum CombatStatus {
        ONGOING,
        PLAYERS_WIN,
        ENEMIES_WIN
    }

    // Runs the full combat until either all players or all enemies are defeated.
    public CombatStatus runCombat(List<Player> players, List<Enemy> enemies, KillFeed killFeed) {
        while(checkCombatStatus(players, enemies) == CombatStatus.ONGOING) {
            runRound(players, enemies, killFeed);
        }

        return checkCombatStatus(players, enemies);
    }

    // Runs one complete combat round using a shuffled turn order.
    public void runRound(List<Player> players, List<Enemy> enemies, KillFeed killFeed) {

        prepareRound(players, enemies);

        for(Entity entity : turnOrder) {
            if(entity instanceof Player && !entity.isDied()) {
                takeTurn(entity, enemies, killFeed);
            }
            else if(entity instanceof Enemy && !entity.isDied()) {
                takeTurn(entity, players, killFeed);
            }

            if(checkCombatStatus(players, enemies) != CombatStatus.ONGOING) {
                return;
            }
        }
    }

    // Prepares a new round by creating and shuffling the attack order.
    public void prepareRound(List<Player> players, List<Enemy> enemies) {
        turnOrder.clear();

        turnOrder.addAll(players);
        turnOrder.addAll(enemies);

        Collections.shuffle(turnOrder);

        currentTurn = 0;
        currentRound++;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    // Performs one attack at a time so the GUI can display combat live.
    public void oneAttack(List<Player> players, List<Enemy> enemies, KillFeed killFeed) {
        
        if(checkCombatStatus(players, enemies) != CombatStatus.ONGOING) {
            return;
        }

        if(turnOrder.isEmpty() || currentTurn >= turnOrder.size()) {
            prepareRound(players, enemies);
        }

        Entity attacker = turnOrder.get(currentTurn);
        currentTurn++;

        if(attacker instanceof Player && !attacker.isDied()) {
            takeTurn(attacker, enemies, killFeed);
        }
        else if(attacker instanceof Enemy && !attacker.isDied()) {
            takeTurn(attacker, players, killFeed);
        }
    }

    // Selects a random target, performs the attack, and removes defeated targets.
    public <T extends Entity> void takeTurn(Entity attacker, List<T> targets, KillFeed killFeed) {
        try {

            TargetSelector<T> selector = new TargetSelector<>();
            T target = selector.selectRandomTarget(targets);

            attacker.attack(target);

            if(target.isDied()) {
                killFeed.addKill(attacker.getName(), target.getName());
                targets.remove(target);
            }

        } catch (NoTargetsAvailableException e) {
            System.out.println(e.getMessage());
        }
    }    

    // Checks whether the combat is still ongoing or one team has won.
    public CombatStatus checkCombatStatus(List<Player> players, List<Enemy> enemies) {
        if(enemies == null || enemies.isEmpty()) {
            return CombatStatus.PLAYERS_WIN;
        }
        else if(players == null || players.isEmpty()) {
            return CombatStatus.ENEMIES_WIN;
        }

        return CombatStatus.ONGOING;
    }
}