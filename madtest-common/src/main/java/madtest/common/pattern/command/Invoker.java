package madtest.common.pattern.command;

/**
 * Created by qct on 2015/6/8.
 */
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action() {
        command.execute();
    }
}
