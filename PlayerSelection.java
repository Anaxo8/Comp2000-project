import Entities.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerSelection {
    private final List<selectionobserver> observers = new ArrayList<>();
    private Player selected;

    public void registerobserver(selectionobserver observer)
    {
        observers.add(observer);
    }

    public void unregisterobserver(selectionobserver observer)
    {
        observers.remove(observer);
    }

    public void select(Player player)
    {
        selected = player;
        notifyObservers();
    }

    public Player getSelected(){
        return selected;
    }

    private void notifyObservers()
    {
        for(selectionobserver observer : observers)
        {
            observer.playerSelected(selected);
        }
    }
}