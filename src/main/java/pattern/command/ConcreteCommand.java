package pattern.command;

/**
 * Created by qct on 2015/6/8.
 */
public class ConcreteCommand implements Command {

    Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
