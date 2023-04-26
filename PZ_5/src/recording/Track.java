package recording;
import style.TrackStyles;

public class Track {
    private String _name;
    private Double _duration;
    private TrackStyles _style;

    public Track(String name, double duration, TrackStyles style) {
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