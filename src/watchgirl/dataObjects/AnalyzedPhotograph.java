package watchgirl.dataObjects;

import java.util.UUID;

public class AnalyzedPhotograph {

    private final Photograph photograph;
    private final PhotographStatus status;

    public AnalyzedPhotograph(Photograph photo, PhotographStatus status) {
        this.photograph = photo;
        this.status = status;
    }

    public PhotographStatus getStatus() {
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
