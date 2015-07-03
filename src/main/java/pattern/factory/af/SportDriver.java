package pattern.factory.af;

/**
 * 具体工厂
 */
public class SportDriver extends Driver3 {
    public BenzCar createBenzCar(String car) throws Exception {
        return (BenzCar) Class.forName(car).newInstance();
    }

    public BmwCar createBmwCar(String car) throws Exception {
        return (BmwCar) Class.forName(car).newInstance();
    }

    public AudiCar createAudiCar(String car) throws Exception {
        return (AudiCar) Class.forName(car).newInstance();
    }
}
