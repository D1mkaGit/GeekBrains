package bridge;

public abstract class Message {
    protected final Sender sender;
    protected String msg;

    public Message(Sender sender) {
        this.sender = sender;
    }

    public abstract void send();

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
