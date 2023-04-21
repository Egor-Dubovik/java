package recording;

import java.util.*;

import style.TrackStyles;

public class TrackService {
    private final String TOTAL_DURATION = "Total duration: ";
    private Double duration = 0.0;

    public void getTracklist(List<Track> tracklist) {
        System.out.println("All traks in list: ");
        if (tracklist.size() == 0) System.out.println("Список пуст.");
        for (Track t : tracklist) {
            System.out.println(t.toString());
        }
        System.out.println();
    }

    public void getTotalDuration(List<Track> tracklist) {

        try {
            for (int i = 0; i < tracklist.size(); i++) {
                duration += tracklist.get(i).getDuration();
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException err) {
            System.out.println("Handling exception:" + err);
        }

        System.out.println(TOTAL_DURATION + duration);
        System.out.println();
    }

    public void sortingByStyle(List<Track> tracklist, TrackStyles style) {
        System.out.println("Sorting by style '" + style + "':");
        for (Track t : tracklist) {
            if (t.getStyle() == style) {
                System.out.println(t.toString());
            }
        }
        for (Track t : tracklist) {
            if (t.getStyle() != style) {
                System.out.println(t.toString());
            }
        }
        System.out.println();
    }

    public void findByRange(Double from, Double to, List<Track> tracklist) {
        System.out.println("All traks with range from: " + from + " to: " + to);
        for (int i = 0; i < tracklist.size(); i++) {
            if (tracklist.get(i).getDuration() >= from && tracklist.get(i).getDuration() <= to) {
                System.out.println(tracklist.get(i).toString());
            }
        }
    }
}