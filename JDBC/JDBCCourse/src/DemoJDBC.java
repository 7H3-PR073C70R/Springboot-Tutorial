import java.sql.*; // step 1

public class DemoJDBC {
    public static void main(String[] args) {
        /*
        1. import packages
        2. load and register
        3. create connection
        4. create statement
        5. execute statement
        6. process result
        7. close
         */


        try {
            String url = "jdbc:postgresql://localhost:5432/demo";
            String username = "postgres";
            String password = "1Shadow@24434!";
            String query = "SELECT * FROM student ORDER BY \"sid\" ASC ";

            // step 2
            // Class.forName("org.postgresql.Driver");

            // step 3
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student values(?, ?, ?)");

            for(int i = 11; i <= 20; i++) {
               preparedStatement.setInt(1, i);
               preparedStatement.setString(2, "7H3 PR073C70R");
               preparedStatement.setInt(3, 10 * i);
               preparedStatement.execute();
            }

//            result.next();
//            String name = result.getString("sname");
//            System.out.println(name);
            Statement statement = connection.createStatement();
            ResultSet result =  statement.executeQuery(query);

            while (result.next()) {
                System.out.print(result.getInt(1) + " : ");
                System.out.print(result.getString(2)  + " : ");
                System.out.println(result.getInt(3));
            }

            connection.close();
            System.out.println("Connection Closed");


        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
