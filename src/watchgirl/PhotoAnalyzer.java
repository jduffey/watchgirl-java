package watchgirl;

public class PhotoAnalyzer {

    public boolean compareSignal(SignalOutput receivedSignal, SignalOutput expectedSignal) {
        return receivedSignal == expectedSignal;
    }
}
