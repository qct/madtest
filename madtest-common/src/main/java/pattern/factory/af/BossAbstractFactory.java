package pattern.factory.af;

/**
 * Created by qct on 2015/6/4.
 */
public class BossAbstractFactory {
    public static void main(String[] args) throws Exception {
        Driver3 driver = new BusinessDriver();
        AudiCar car = driver.createAudiCar("pattern.factory.af.AudiSportCar");
        car.setName("Audi Q5");
        car.drive();
    }
}
