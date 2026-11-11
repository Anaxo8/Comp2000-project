import Entities.Entity;
import java.util.List;

@FunctionalInterface
public interface TargetingStrategy<T extends Entity> {
    T selectTarget(List<T> targets) throws NoTargetsAvailableException;
}
