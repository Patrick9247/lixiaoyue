import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/lixiaoyue";
        String user = "root";
        String pwd = "root";
        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            System.out.println("连接失败：" + e.getMessage());
        }
    }
}