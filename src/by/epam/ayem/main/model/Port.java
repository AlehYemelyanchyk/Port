package by.epam.ayem.main.model;

/* Задание 4. Многопоточность. Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров.
Число контейнеров, находящихся в текущий момент в порту и на корабле, должно быть неотрицательным
и не превышающим заданную грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
У одного причала может стоять один корабль. Корабль может загружаться у причала или разгружаться. */

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Aleh Yemelyanchyk on 10/2/2019.
 */
public class Port {
    private final String name;
    private final int capacity;
    private final Map<Cargo, Integer> cargo;
    private final List<Dock> docks;
    private final Queue<Ship> shipQueue;

    public Port(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.cargo = new ConcurrentHashMap<>();
        this.docks = new CopyOnWriteArrayList<>();
        this.shipQueue = new PriorityBlockingQueue<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public Map<Cargo, Integer> getCargo() {
        return cargo;
    }

    public List<Dock> getDocks() {
        return docks;
    }

    public Queue<Ship> getShipQueue() {
        return shipQueue;
    }
}
