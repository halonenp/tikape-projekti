package arkistoprojekti;

import java.util.*;
import java.sql.*;

public class RaakaAineDao implements Dao<RaakaAine, Integer> {

    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, nimi FROM RaakaAine WHERE id = ?");
            stmt.setInt(1, key);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                return new RaakaAine(result.getInt("id"), result.getString("nimi"));
            }
        }

        return null;
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {
        List<RaakaAine> aineet = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id, nimi FROM RaakaAine").executeQuery()) {

            while (result.next()) {
                aineet.add(new RaakaAine(result.getInt("id"), result.getString("nimi")));
            }
        }

        return aineet;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM RaakaAine WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public RaakaAine saveOrUpdate(RaakaAine object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO RaakaAine (nimi) VALUES (?)");
            stmt.setString(1, object.getNimi());

            stmt.executeUpdate();
        }

        return null;
    }

    @Override
    public void save(RaakaAine aine) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO RaakaAine"
                + " (nimi)"
                + " VALUES (?)");
        stmt.setString(1, aine.getNimi());

        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT * FROM RaakaAine"
                + " WHERE nimi = ?");
        stmt.setString(1, aine.getNimi());

        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos

        Ruoka a = new Ruoka(rs.getInt("id"), rs.getString("nimi"));

        stmt.close();
        rs.close();

        conn.close();

    }

    @Override
    public void update(RaakaAine aine) throws SQLException {

        throw new UnsupportedOperationException("Not supported yet.");
    }
}
