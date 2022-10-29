import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String psw = "Battery-10*-";
        String SQL1 = "SELECT name, count(*)/((date_format (max(subscription_date), '%m') " +
                     "- date_format (min(subscription_date), '%m')) + 1)  as avrg ";
        String SQL2 = "FROM courses AS c JOIN subscriptions AS s ON  c.id = s.course_id " +
                      "group by name;";
        try {
            Connection connection= DriverManager.getConnection(url, user, psw);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL1 + SQL2);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " "
                        + Math.round(resultSet.getFloat("avrg")*1000f)/1000f);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch(SQLException ex){ex.printStackTrace();}
    }
}
