package by.epam.ayem.main.service;

import by.epam.ayem.main.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Aleh Yemelyanchyk on 10/7/2019.
 */
public class PortService {

    private Random random = new Random();
    private Map<Integer, String> colorMap = new HashMap<>();
    private ShipService shipService = new ShipService();

    public PortService() {
        colorMap.put(0, ColorScheme.RED);
        colorMap.put(1, ColorScheme.GREEN);
        colorMap.put(2, ColorScheme.YELLOW);
        colorMap.put(3, ColorScheme.BLUE);
    }

    public void countCargoPort(Port port) {
        System.out.println(ColorScheme.RESET + "Port has " + getOccupancy(port));
    }

    public void countCargoShips(Ship... ships) {
        int containers = 0;
        for (Ship ship : ships) {
            for (Map.Entry<Cargo, Integer> entry : ship.getCargo().entrySet()) {
                Integer amount = entry.getValue();
                containers += amount;
            }
        }
        System.out.println(ColorScheme.RESET + "Ships has " + containers);
    }

    public void addDock(Port port, Dock dock) {
        port.getDocks().add(dock);
    }

    public void addCargo(Port port, Cargo cargo, int amount) {
        for (int i = 0; i <= amount; i++) {
            if (isEnoughCapacity(port, amount)) {
                port.getCargo().put(cargo, amount);
            } else {
                return;
            }
        }
    }

    public void addShipToQueue(Port port, Ship ship) {
        port.getShipQueue().offer(ship);
    }

    public void sendToDock(Port port) {
        Ship nextShip = port.getShipQueue().poll();
        int dockNumber = getDockNumber(port, nextShip);
        if (nextShip != null) {
            if (shipService.getCargoWeight(nextShip) < nextShip.getLoadCapacityTn() / 2) {
                System.out.println(ColorScheme.RESET + nextShip.getName() + " arrived to "
                        + port.getDocks().get(dockNumber).getName());
                loadContainers(port, dockNumber, nextShip);
            } else {
                System.out.println(ColorScheme.RESET + nextShip.getName() + " arrived to "
                        + port.getDocks().get(dockNumber).getName());
                unloadContainers(port, dockNumber, nextShip);
            }
        } else {
            System.out.println("There are no ships in the queue left");
        }
        System.out.println(ColorScheme.RESET + nextShip.getName() + " left "
                + port.getDocks().get(dockNumber).getName());
        port.getDocks().get(dockNumber).setShip(null);
        addShipToQueue(port, nextShip);
    }

    private synchronized int getDockNumber(Port port, Ship ship) {
        List<Dock> docks = port.getDocks();
        for (int i = 0; i < docks.size(); i++) {
            if (docks.get(i).getShip() == null) {
                docks.get(i).setShip(ship);
                return i;
            }
        }
        throw new RuntimeException("Got an occupied dock");
    }

    private void loadContainers(Port port, int dockNumber, Ship ship) {
        String color = colorMap.get(dockNumber);
        Cargo cargo = getRandomCargo();

        int amountOfCargo = random.nextInt(10);

        for (int i = 0; i <= amountOfCargo; i++) {
            if (isEnoughCargo(port, cargo) &&
                    shipService.isEnoughWeightCapacity(ship, cargo)) {
                try {
                    Thread.sleep(cargo.getLoadTimeMin());
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage() + "loading...");
                }
                Integer amount = port.getCargo().get(cargo);
                port.getCargo().put(cargo, --amount);
                shipService.loadCargo(ship, cargo);
                showOperationInfo(port, dockNumber, ship, color, cargo);
            }
        }
    }

    private void unloadContainers(Port port, int dockNumber, Ship ship) {
        String color = colorMap.get(dockNumber);
        port.getDocks().get(dockNumber).setShip(ship);
        Cargo cargo = getRandomCargo();

        int amountOfCargo = random.nextInt(10);

        for (int i = 0; i <= amountOfCargo; i++) {
            if (isEnoughCapacity(port, amountOfCargo) &&
                    shipService.isEnoughCargo(ship, cargo)) {
                try {
                    Thread.sleep(cargo.getLoadTimeMin());
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage() + "loading...");
                }
                Integer amount = port.getCargo().get(cargo);
                port.getCargo().put(cargo, ++amount);
                shipService.unloadCargo(ship, cargo);
                showOperationInfo(port, dockNumber, ship, color, cargo);
            }
        }
    }

    private void showOperationInfo(Port port, int dockNumber, Ship ship, String color, Cargo cargo) {
        System.out.println(color +
                cargo.getName() + ": " +
                port.getDocks().get(dockNumber).getName() + ". " +
                port.getName() + "(" +
                getOccupancy(port) + "/" +
                port.getCapacity() + " items). " +
                ship.getName() + "(" +
                shipService.getCargoWeight(ship) + "/" +
                ship.getLoadCapacityTn() + " tn)");
    }

    private boolean isEnoughCargo(Port port, Cargo cargo) {
        for (Map.Entry<Cargo, Integer> entry : port.getCargo().entrySet()) {
            Cargo cargoItem = entry.getKey();

            if (cargoItem.equals(cargo)) {
                if (entry.getValue() >= 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean isEnoughCapacity(Port port, int amount) {
        return getOccupancy(port) + amount <= port.getCapacity();
    }

    private int getOccupancy(Port port) {
        int amountOfCargo = 0;
        for (Map.Entry<Cargo, Integer> entry : port.getCargo().entrySet()) {
            amountOfCargo += entry.getValue();
        }
        return amountOfCargo;
    }

    private Cargo getRandomCargo() {
        Cargo cargo = null;
        int i = random.nextInt(4);
        switch (i) {
            case 0:
                cargo = new Cargo("Dry bulk", 5, 50);
                break;
            case 1:
                cargo = new Cargo("Liquid bulk", 3, 30);
                break;
            case 2:
                cargo = new Cargo("Break bulk", 2, 20);
                break;
            case 3:
                cargo = new Cargo("Container", 10, 5);
                break;
        }
        return cargo;
    }
}
