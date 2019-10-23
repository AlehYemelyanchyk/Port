package by.epam.ayem.main.model;

/* Задание 4. Многопоточность. Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров.
Число контейнеров, находящихся в текущий момент в порту и на корабле, должно быть неотрицательным
и не превышающим заданную грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
У одного причала может стоять один корабль. Корабль может загружаться у причала или разгружаться. */

/**
 * @author Aleh Yemelyanchyk on 10/23/2019.
 */
public class CargoBuilder {
    private Cargo cargo = new Cargo();

    public CargoBuilder addName(String name) {
        cargo.setName(name);
        return this;
    }

    public CargoBuilder addWeightTn(int weight) {
        cargo.setWeightTn(weight);
        return this;
    }

    public CargoBuilder addLoadTimeMin(int time) {
        cargo.setLoadTimeMin(time * 10);
        return this;
    }

    public Cargo build() {
        return cargo;
    }
}
