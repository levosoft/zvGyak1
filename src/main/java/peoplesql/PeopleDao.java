package peoplesql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleDao {

    private DataSource dataSource;

    public PeopleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String findIpByName(String firstName, String lastName) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement("SELECT ip_address FROM people WHERE first_name = ? and last_name = ?");
        ){
            pst.setString(1, firstName);
            pst.setString(2, lastName);


            //A ResultSet külön metódusba van kiszervezve! -> Külön try-with resourcesben kell kezelni a ResultSet lezárását!!!
            return selectIpByPreparedStatement(pst);
        }
        catch (SQLException se){
            throw new IllegalStateException("Cannot query", se);
        }
    }

    private String selectIpByPreparedStatement(PreparedStatement pst) {
        try (
                ResultSet rs = pst.executeQuery()
        ) {
            if(rs.next()) {
                String ip = rs.getString("ip_address");
                return ip;
            }
            throw new IllegalArgumentException("Not found");
        }catch (SQLException sqle){
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

}
