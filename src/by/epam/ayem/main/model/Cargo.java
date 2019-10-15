package by.epam.ayem.main.model;

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
