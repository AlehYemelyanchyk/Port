package by.epam.ayem.main.model;

/* Задание 4. Многопоточность. Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров.
Число контейнеров, находящихся в текущий момент в порту и на корабле, должно быть неотрицательным
и не превышающим заданную грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
У одного причала может стоять один корабль. Корабль может загружаться у причала или разгружаться. */

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleh Yemelyanchyk on 10/2/2019.
 */
public class Ship implements Comparable {
    private final String name;
    private final int loadCapacityTn;
    private final Map<Cargo, Integer> cargo;

    public Ship(String name, int loadCapacityTn) {
        this.name = name;
        this.loadCapacityTn = loadCapacityTn;
        this.cargo = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getLoadCapacityTn() {
        return loadCapacityTn;
    }

    public Map<Cargo, Integer> getCargo() {
        return cargo;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
