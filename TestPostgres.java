package testpostgres;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestPostgres {

    public static void main(String[] args) {
       Connection connection;
       Statement stmt;
       ResultSet rs;
       String errMessage = null;
       
       try{
           
           Class.forName("org.postgresql.Driver").newInstance();
           
           //rafael.chumarin - название БД в СУБД Postgres
           //user1 - владелец таблиц в БД "rafael.chumarin"
           connection = DriverManager.getConnection(                
                    "jdbc:postgresql://localhost:5432/rafael.chumarin", "user1", "p@ssw0rd");
           String query = "select messages.id, users.fname, users.clientid, messages.message from messages, users where messages.clientid = users.clientid;";
           stmt = connection.createStatement();
           rs = stmt.executeQuery(query);
           System.out.println(connection.isClosed());
           try{
              while(rs.next()){             
                  System.out.println(rs.getString(1)
                         + "   \t" + rs.getString(2)
                         + "   \t" + rs.getString(3)
                         + "   \t" + rs.getString(4));                         
              }
              rs.close();
           } catch (SQLException e) {
               errMessage = e.getMessage();
               System.out.println(errMessage);
             }
       } catch (Exception e) {
           errMessage = e.getMessage();
           System.out.println(errMessage);
         }
       }
    }
