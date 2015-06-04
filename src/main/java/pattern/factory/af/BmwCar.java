package pattern.factory.af;

/**
 * Created by qct on 2015/6/4.
 */
public abstract class BmwCar {
    private String name;

    public abstract void drive();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class BmwSportCar extends BmwCar {
    public void drive() {
        System.out.println(this.getName() + "----BmwSportCar-----------------------");
    }
}

class BmwBusinessCar extends BmwCar {
    public void drive() {
        System.out.println(this.getName() + "----BmwBusinessCar-----------------------");
    }
}
