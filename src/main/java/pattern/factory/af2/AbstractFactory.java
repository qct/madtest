package pattern.factory.af2;

/**
 * Created by qct on 2015/6/4.
 */
public interface AbstractFactory {
    /**
     *
     * @return
     */
    Cpu createCpu();

    /**
     *
     * @return
     */
    MainBoard createMainboard();
}
