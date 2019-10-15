package by.epam.ayem.main.model;

/* Задание 4. Многопоточность. Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров.
Число контейнеров, находящихся в текущий момент в порту и на корабле, должно быть неотрицательным
и не превышающим заданную грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
У одного причала может стоять один корабль. Корабль может загружаться у причала или разгружаться. */

/**
 * @author Aleh Yemelyanchyk on 10/7/2019.
 */
public class Dock {
    private final String name;
    private Ship ship;

    public Dock(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
