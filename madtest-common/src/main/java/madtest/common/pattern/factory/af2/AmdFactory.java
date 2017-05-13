package madtest.common.pattern.factory.af2;

/**
 * Created by qct on 2015/6/4.
 */
public class AmdFactory implements AbstractFactory {

    public AmdFactory() {
        System.out.println("AmdFactory init---");
    }

    @Override
    public Cpu createCpu() {
        System.out.println("AmdFactory create CPU: ");
        return new AmdCpu();
    }

    @Override
    public MainBoard createMainboard() {
        System.out.println("AmdFactory create MainBoard: ");
        return new AmdMainboard();
    }
}
