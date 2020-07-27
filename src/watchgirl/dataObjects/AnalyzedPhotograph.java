package watchgirl.dataObjects;

import java.util.UUID;

public class AnalyzedPhotograph {

    private final Photograph photograph;
    private final AnalyzedPhotographStatus status;

    public AnalyzedPhotograph(Photograph photo, AnalyzedPhotographStatus status) {
        this.photograph = photo;
        this.status = status;
    }

    public AnalyzedPhotographStatus getStatus() {
        return status;
    }

    // Public for testing
    public Photograph getPhoto() {
        return photograph;
    }

    public UUID getPhotoId() {
        return photograph.getPhotoId();
    }

    public String getTime() {
        return photograph.getTime();
    }

    public UUID getCameraId() {
        return photograph.getCameraId();
    }

    public SignalOutput getSignal() {
        return photograph.getSignal();
    }
}
