import java.util.List;
import java.util.Random;

public class TargetSelector<T> {
    public T selectRandomTarget(List<T> targets) throws NoTargetsAvailableException {

        if (targets == null || targets.isEmpty()) {
            throw new NoTargetsAvailableException("No targets available.");
        }

        Random random = new Random();
        int index = random.nextInt(targets.size());

        return targets.get(index);
    }
}
