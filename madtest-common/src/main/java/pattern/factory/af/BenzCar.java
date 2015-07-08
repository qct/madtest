package pattern.factory.af;

/**
 * Created by qct on 2015/6/4.
 */
public abstract class BenzCar {
    private String name;

    public abstract void drive();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class BenzSportCar extends BenzCar {
    public void drive() {
        System.out.println(this.getName() + "----BenzSportCar-----------------------");
    }
}

class BenzBusinessCar extends BenzCar {
    public void drive() {
        System.out.println(this.getName() + "----BenzBusinessCar-----------------------");
    }
}
