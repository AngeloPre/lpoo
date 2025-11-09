package banco;

//import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.Properties;

public class ConnectionFactory {
    //private static Properties properties;
    //Garente que não haverá instâncias da ConnectionFactory
    private ConnectionFactory(){
    }
    public static Connection getConnection() throws 
            SQLException, IOException{
        //readProperties();
        String url = "jdbc:postgresql://localhost:5432/locadora";
        String user = "postgres";
        String pwd = "postgres";
        return DriverManager.getConnection(
                url,user,pwd);
    }
    //private static void readProperties() throws IOException
    //{
    //    if(properties==null){
    //        Properties props = new Properties();
    //        FileInputStream file = new FileInputStream("./db.properties");
    //        props.load(file);
    //        properties = props ;
    //    }
    //}
}
