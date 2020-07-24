package watchgirl;

public class SignalComparer {

    public boolean compareSignal(SignalOutput receivedSignal, SignalOutput expectedSignal) {
        return receivedSignal == expectedSignal;
    }
}
