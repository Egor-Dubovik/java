package recording;
import style.TrackStyles;
import java.util.Optional;

public class Track {
    private Optional<Integer> id;
    private String _name;
    private Double _duration;
    private TrackStyles _style;

    public Track(String name, double duration, TrackStyles style) {
        this.id = Optional.empty();
        this._name = name;
        this._duration = duration;
        this._style = style;
    }
    public Track(int id, String name, double duration, TrackStyles style) {
        this.id = Optional.of(id);
        this._name = name;
        this._duration = duration;
        this._style = style;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public Double getDuration() {
        return _duration;
    }

    public void setDuration(Double duration) {
        this._duration = duration;
    }

    public TrackStyles getStyle() {
        return _style;
    }

    public void setStyle(TrackStyles style) {
        this._style = style;
    }

    public String toString() {
        return "Name: " + getName() + ", Duration: " + getDuration() + ", Style: " + getStyle();
    }
}