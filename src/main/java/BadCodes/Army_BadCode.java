package BadCodes;

import org.example.entities.Knight;
import org.example.entities.Warrior;

import java.util.ArrayDeque;

public class Army_BadCode {

    private final ArrayDeque<Warrior> units;

    public Army_BadCode() {
        units = new ArrayDeque<>();
    }

    public void addUnits(Warrior warrior, int quantity) {

        if (warrior instanceof Knight) {
            while (quantity-- > 0) {
                units.offer(new Knight());
            }
        } else {
            while (quantity-- > 0) {
                units.offer(new Warrior());
            }
        }
    }

    public boolean win() {
        return units.size() > 0;
    }

    public ArrayDeque<Warrior> getUnits() {
        return units;
    }
}
