package bridge;

public class ClientSender implements Sender {
    @Override
    public void getSenderType() {
        System.out.print("Client: ");
    }
}
