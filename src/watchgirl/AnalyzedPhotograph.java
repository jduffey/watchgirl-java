package watchgirl;

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

    public Photograph getPhoto() {
        return photograph;
    }
}
