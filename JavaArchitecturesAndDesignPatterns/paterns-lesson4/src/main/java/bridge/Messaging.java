package bridge;

public class Messaging extends Message {

    public Messaging(Sender sender) {
        super(sender);
    }

    @Override
    public void send() {
        sender.getSenderType();
        System.out.println(getMsg());
    }
}
