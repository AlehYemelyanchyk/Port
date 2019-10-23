package by.epam.ayem.main.service;

/* Задание 4. Многопоточность. Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров.
Число контейнеров, находящихся в текущий момент в порту и на корабле, должно быть неотрицательным
и не превышающим заданную грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
У одного причала может стоять один корабль. Корабль может загружаться у причала или разгружаться. */

import by.epam.ayem.main.model.*;

public class PortMain {

    private ShipService shipService = new ShipService();
    private PortService portService = new PortService();

    public void run() {
        Port port = new Port("Port Arthur", 500);

        Dock dock1 = new Dock("DOCK_A");
        Dock dock2 = new Dock("DOCK_B");
        Dock dock3 = new Dock("DOCK_C");
        Dock dock4 = new Dock("DOCK_D");

        portService.addDock(port, dock1);
        portService.addDock(port, dock2);
        portService.addDock(port, dock3);
        portService.addDock(port, dock4);

        Cargo cargo1 = new CargoBuilder().addName("Dry bulk").addWeightTn(5).addLoadTimeMin(50).build();
        Cargo cargo2 = new CargoBuilder().addName("Liquid bulk").addWeightTn(3).addLoadTimeMin(30).build();
        Cargo cargo3 = new CargoBuilder().addName("Break bulk").addWeightTn(2).addLoadTimeMin(20).build();
        Cargo cargo4 = new CargoBuilder().addName("Container").addWeightTn(10).addLoadTimeMin(5).build();

        portService.addCargo(port, cargo1, 50);
        portService.addCargo(port, cargo2, 10);
        portService.addCargo(port, cargo3, 200);
        portService.addCargo(port, cargo4, 130);

        Ship ship1 = new Ship("Queen Marry", 90);

        shipService.addCargo(ship1, cargo1, 3);
        shipService.addCargo(ship1, cargo2, 5);

        Ship ship2 = new Ship("Admiral Kapralov", 140);

        shipService.addCargo(ship2, cargo3, 3);
        shipService.addCargo(ship2, cargo2, 5);

        Ship ship3 = new Ship("Potsiomkin", 100);

        shipService.addCargo(ship3, cargo1, 3);
        shipService.addCargo(ship3, cargo2, 3);
        shipService.addCargo(ship3, cargo4, 5);

        Ship ship4 = new Ship("Lvov", 75);
        Ship ship5 = new Ship("Tolstoy", 90);

        portService.addShipToQueue(port, ship1);
        portService.addShipToQueue(port, ship2);
        portService.addShipToQueue(port, ship3);
        portService.addShipToQueue(port, ship4);
        portService.addShipToQueue(port, ship5);

        portService.countCargoPort(port);
        portService.countCargoShips(ship1, ship2, ship3, ship4, ship5);

        for (int i = 0; i <= 3; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 <= 2; i1++) {
                    portService.sendToDock(port);
                }
            }).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        portService.countCargoPort(port);
        portService.countCargoShips(ship1, ship2, ship3, ship4, ship5);
    }
}
