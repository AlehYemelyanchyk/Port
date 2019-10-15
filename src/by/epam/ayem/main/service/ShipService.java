package by.epam.ayem.main.service;

import by.epam.ayem.main.model.Cargo;
import by.epam.ayem.main.model.Ship;

import java.util.Map;

/**
 * @author Aleh Yemelyanchyk on 10/11/2019.
 */
public class ShipService {

    public void addCargo(Ship ship, Cargo cargo, int amount) {
        for (int i = 0; i <= amount; i++) {
            if (isEnoughWeightCapacity(ship, cargo)) {
                ship.getCargo().put(cargo, amount);
            }
        }
    }

    public void loadCargo(Ship ship, Cargo cargo) {
        for (Map.Entry<Cargo, Integer> entry : ship.getCargo().entrySet()) {
            Cargo cargoItem = entry.getKey();
            Integer amount = entry.getValue();

            if (cargoItem.equals(cargo)) {
                entry.setValue(amount + 1);
                return;
            }
        }
        ship.getCargo().put(cargo, 1);
    }

    public void unloadCargo(Ship ship, Cargo cargo) {
        for (Map.Entry<Cargo, Integer> entry : ship.getCargo().entrySet()) {
            Cargo cargoItem = entry.getKey();
            Integer amount = entry.getValue();

            if (cargoItem.equals(cargo)) {
                entry.setValue(amount - 1);
                return;
            }
        }
        ship.getCargo().put(cargo, 1);
    }

    public boolean isEnoughWeightCapacity(Ship ship, Cargo cargo) {
        return (getCargoWeight(ship) + cargo.getWeightTn()) <= ship.getLoadCapacityTn();
    }

    public boolean isEnoughCargo(Ship ship, Cargo cargo) {
        for (Map.Entry<Cargo, Integer> entry : ship.getCargo().entrySet()) {
            Cargo cargoItem = entry.getKey();

            if (cargoItem.equals(cargo)) {
                return entry.getValue() >= 1;
            }
        }
        return false;
    }

    public int getCargoWeight(Ship ship) {
        int weight = 0;
        for (Map.Entry<Cargo, Integer> entry : ship.getCargo().entrySet()) {
            weight += entry.getKey().getWeightTn() * entry.getValue();
        }
        return weight;
    }
}
