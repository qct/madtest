package madtest.common.pattern.factory.af2;

/**
 * Created by qct on 2015/6/4.
 */
public class IntelFactory implements AbstractFactory {

    public IntelFactory() {
        System.out.println("IntelFactory init---");
    }

    @Override
    public Cpu createCpu() {
        return new IntelCpu();
    }

    @Override
    public MainBoard createMainboard() {
        return new IntelMainboard();
    }
}
