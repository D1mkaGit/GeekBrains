package bridge;

public class ManagerSender implements Sender {
    @Override
    public void getSenderType() {
        System.out.print("Manager: ");
    }
}
