package bridge;

public class AdminSender implements Sender {
    @Override
    public void getSenderType() {
        System.out.print("Admin: ");
    }
}
