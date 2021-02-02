package bridge;

public class Main {
    public static void main(String[] args) {
        Messaging clientMessage = new Messaging(new ClientSender());
        clientMessage.setMsg("Hello my name is Vasja, I'm new employee");
        clientMessage.send();

        Messaging adminMessage = new Messaging(new AdminSender());
        adminMessage.setMsg("Hello I'm your admin, please contact your manager");
        adminMessage.send();

        Messaging managerMessage = new Messaging(new ManagerSender());
        managerMessage.setMsg("Hello I'm your manager, Welcome to our team. I will come to your desk in few moments and will introduce you to our team");
        managerMessage.send();
    }
}
