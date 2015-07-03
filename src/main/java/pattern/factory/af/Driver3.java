package pattern.factory.af;

/**
 * 抽象工厂
 * Created by qct on 2015/6/4.
 */
public abstract class Driver3 {
    public abstract BenzCar createBenzCar(String car) throws Exception;

    public abstract BmwCar createBmwCar(String car) throws Exception;

    public abstract AudiCar createAudiCar(String car) throws Exception;
}

