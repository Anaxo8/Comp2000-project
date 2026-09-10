import java.util.List;
import Entities.Entity;

public class CombatController {
    public <T extends Entity> void takeTurn(Entity attacker, List<T> targets, KillFeed killFeed) {
        try {

            TargetSelector<T> selector = new TargetSelector<>();
            T target = selector.selectRandomTarget(targets);

            attacker.attack(target);

            if(target.getHp() <= 0) {
                killFeed.addKill(attacker.getName(), target.getName());
                targets.remove(target);
            }

        } catch (NoTargetsAvailableException e) {
            System.out.println(e.getMessage());
        }
    }    
}
