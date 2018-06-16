package arkistoprojekti;

import java.util.*;

import java.sql.*;

public class RuokaRaakaAineDao implements Dao<RuokaRaakaAine, Integer> {

    private Database database;
    private Dao<Ruoka, Integer> ruokaDao;
    private Dao<RaakaAine, Integer> raakaaineDao;

    public RuokaRaakaAineDao(Database database, Dao<Ruoka, Integer> ruokaDao, Dao<RaakaAine, Integer> raakaaineDao) {
        this.database = database;
        this.ruokaDao = ruokaDao;
        this.raakaaineDao = raakaaineDao;
    }

    @Override
    public RuokaRaakaAine findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RuokaRaakaAine> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM RuokaRaakaAine WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void saveOrUpdate(RuokaRaakaAine rra) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO RuokaRaakaAine"
                + " (jarjestys, maara, ohje, ruoka_id, raaka_aine_id)"
                + " VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, rra.getJarjestys());
        stmt.setInt(2, rra.getMaara());
        stmt.setString(3, rra.getOhje());
        stmt.setInt(4, rra.getRuokaid());
        stmt.setInt(5, rra.getRaakaAineid());

        stmt.executeUpdate();
        stmt.close();

        ResultSet rs = stmt.executeQuery();
        rs.next();

        stmt.close();
        rs.close();

        conn.close();

    }

    public void save(RuokaRaakaAine rra) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(RuokaRaakaAine rra) throws SQLException {

        throw new UnsupportedOperationException("Not supported yet.");
    }
}
