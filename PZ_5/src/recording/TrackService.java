package recording;

import java.util.*;
import style.TrackStyles;

public class TrackService {
	private final String TOTAL_DURATION = "Total duration: ";
	private Double duration = 0.0;

	public void getTracklist(List<Track> tracklist) {
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
			} catch(ArrayIndexOutOfBoundsException | NumberFormatException err) {
				System.out.println("Обработка исключений:" + err);
			}catch (NullPointerException e) {
				System.out.println("Трэк лист пустой!");
			}
		System.out.println(TOTAL_DURATION + duration);
		System.out.println();
	}

	public void sortingByStyle(List<Track> tracklist, TrackStyles style) {
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
		try {
			if (from > to) {
				throw new IllegalArgumentException("'from' should be less than 'to'");
			}
			for (int i = 0; i < tracklist.size(); i++) {
				if (tracklist.get(i).getDuration() >= from && tracklist.get(i).getDuration() <= to) {
					System.out.println(tracklist.get(i).toString());
				}
			}
		} catch(IllegalArgumentException error) {
			System.out.println("исключений exception: " + error);
		}
	}
}