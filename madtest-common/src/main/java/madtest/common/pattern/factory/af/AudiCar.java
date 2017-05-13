package madtest.common.pattern.factory.af;

/**
 * Created by qct on 2015/6/4.
 */
public abstract class AudiCar {

    private String name;

    public abstract void drive();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class AudiSportCar extends AudiCar {

    public void drive() {
        System.out.println(this.getName() + "----AudiSportCar-----------------------");
    }
}

class AudiBusinessCar extends AudiCar {

    public void drive() {
        System.out.println(this.getName() + "----AudiBusinessCar-----------------------");
    }
}

