package state;

public class Worker {
    private Activity state;

    public void setState(Activity state) {
        this.state = state;
    }

    void doActivity() {
        if (state instanceof Work) {
            setState(new Rest());
        } else {
            setState(new Work());
        }
        state.doActivity();
    }
}
