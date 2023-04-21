import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import recording.Track;
import style.TrackStyles;

public class DatabaseHandler extends Configs {
    Connection dbConnection = null;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(url, dbUser, dbPass);
        return dbConnection;
    }

    public void createTrack(String name, double duration, TrackStyles style) {
        String insertTrack = "INSERT INTO " + Constants.TRACK_TABLE + "(" +
                Constants.TRACK_NAME + "," + Constants.TRACK_DURATION + "," +
                Constants.TRACK_STYLE + ")" +
                "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertTrack);
            prSt.setString(1, name);
            prSt.setDouble(2, duration);
            prSt.setString(3, String.valueOf(style));
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public List<Track> getAllTracks() {
        String selectTracks = "SELECT * FROM " + Constants.TRACK_TABLE;
        List<Track> tracks = new ArrayList<>();

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectTracks);
            ResultSet response = prSt.executeQuery();
            while (response.next()) {
                int id = response.getInt("id");
                String name = response.getString("name");
                Double duration = response.getDouble("duration");
                TrackStyles style = TrackStyles.valueOf(response.getString("style"));

                Track track = new Track(id, name, duration, style);
                tracks.add(track);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tracks;
    }
}
