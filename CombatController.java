import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import Entities.Entity;
import Entities.Player;
import Entities.Enemy;

public class CombatController {

    public enum CombatStatus {
        ONGOING,
        PLAYERS_WIN,
        ENEMIES_WIN
    }

    public CombatStatus runCombat(List<Player> players, List<Enemy> enemies, KillFeed killFeed) {
        while(checkCombatStatus(players, enemies) == CombatStatus.ONGOING) {
            runRound(players, enemies, killFeed);
        }

        return checkCombatStatus(players, enemies);
    }

    public void runRound(List<Player> players, List<Enemy> enemies, KillFeed killFeed) {
        List<Entity> turnOrder = new ArrayList<>();
        turnOrder.addAll(players);
        turnOrder.addAll(enemies);

        Collections.shuffle(turnOrder);

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