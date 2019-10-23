package by.epam.ayem.main.model;

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
