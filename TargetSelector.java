import java.util.List;
import java.util.Random;
import java.util.Comparator;

import Entities.Entity;

public class TargetSelector<T extends Entity> {
    public T selectRandomTarget(List<T> targets) throws NoTargetsAvailableException {

        if (targets == null || targets.isEmpty()) {
            throw new NoTargetsAvailableException("No targets available.");
        }

        Random random = new Random();
        int index = random.nextInt(targets.size());

        return targets.get(index);
    }

    public T selectLowestHpTarget(List<T> targets) throws NoTargetsAvailableException {
        if(targets == null || targets.isEmpty()) {
            throw new NoTargetsAvailableException("No targets available.");
        }

        return targets.stream().filter(target -> !target.isDied())
        .min(Comparator.comparingInt(target -> target.getHp()))
        .orElseThrow(() -> new NoTargetsAvailableException("No living targets available."));
    }
}
