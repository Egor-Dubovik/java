import recording.*;
import style.TrackStyles;

import java.util.List;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.createTrack("Hero", 2.5, TrackStyles.ROCK);
        dbHandler.createTrack("Love", 3.08, TrackStyles.CLASSIC);
        dbHandler.createTrack("Say Hello", 1.8, TrackStyles.POP);
        dbHandler.createTrack("Fuck me", 2.3, TrackStyles.POP);

        List<Track> tracklist = dbHandler.getAllTracks();
        TrackService service = new TrackService();

        service.getTracklist(tracklist);
        service.getTotalDuration(tracklist);
        service.sortingByStyle(tracklist, TrackStyles.POP);
        service.findByRange(2.49, 3.1, tracklist);
    }
}