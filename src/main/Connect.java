package main;
import java.sql.*;

public class Connect {
    private Statement st;
    private Connection con;
    private static Connect connect = null;

    public static Connect getInstance() {
        if (connect == null) connect = new Connect();
        return connect;
    }

    private  Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/auto_film_shop";
            String user = "root";
            String password = "";
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement(1004, 1008);
            System.out.println("Connection Successful");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }
        return rs;
    }

    public void executeUpdate(String query) {
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }
    }

    public int preparedStatementQuery(String query, String parameter[]){
        try{
            PreparedStatement preparedStmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for(int i=0;i<parameter.length;i++){
                preparedStmt.setString((i+1), parameter[i]);
            }
            int affectedRows = preparedStmt.executeUpdate();
            try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }

        return -1;
    }

    public void close() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
