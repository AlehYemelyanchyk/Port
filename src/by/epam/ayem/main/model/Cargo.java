package by.epam.ayem.main.model;

/* Задание 4. Многопоточность. Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров.
Число контейнеров, находящихся в текущий момент в порту и на корабле, должно быть неотрицательным
и не превышающим заданную грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
У одного причала может стоять один корабль. Корабль может загружаться у причала или разгружаться. */

import java.util.Objects;

/**
 * @author Aleh Yemelyanchyk on 10/10/2019.
 */
public final class Cargo {
    private final String name;
    private final int weightTn;
    private final int loadTimeMin;

    public Cargo(String name, int weightTn, int loadTimeMin) {
        this.name = name;
        this.weightTn = weightTn;
        this.loadTimeMin = loadTimeMin * 10;
    }

    public String getName() {
        return name;
    }

    public int getWeightTn() {
        return weightTn;
    }

    public int getLoadTimeMin() {
        return loadTimeMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return Objects.equals(name, cargo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
