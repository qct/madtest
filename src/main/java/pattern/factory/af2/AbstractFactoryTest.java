package pattern.factory.af2;

/**
 * Created by qct on 2015/6/4.
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory amdFactory = new AmdFactory();
        AbstractFactory intelFactory = new IntelFactory();

        Cpu c1 = amdFactory.createCpu();
        Cpu c2 = intelFactory.createCpu();

        MainBoard mb1 = amdFactory.createMainboard();
        MainBoard mb2 = intelFactory.createMainboard();
    }
}
