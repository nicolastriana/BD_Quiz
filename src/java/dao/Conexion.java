package dao;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class Conexion {
	
	private static Connection CONEXION=null;
    	public static Connection getConnection() throws URISyntaxException{
       /* String HOST = "ec2-107-20-224-137.compute-1.amazonaws.com"; 
        String DATABASE = "d7b7756a79th7m";
        String USER = "kinjplfbuhadca";
        String PASS = "580c68fa17bff94638e49b1125ece327eba8a7731083e1c4c0240111601d2de5";*/


        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        
    
		   if(CONEXION == null){
			  	try {
					CONEXION = DriverManager.getConnection(dbUrl, username, password);
                        	} catch (SQLException e) {
					System.out.println("Connection Failed! Check output console");
					e.printStackTrace();
				}

				
		   }
		   return CONEXION;
	}
	
	public static void closeConnection(){
		 try {
			 if(CONEXION!=null){
				 CONEXION.close();
				 CONEXION=null;
			 }
			 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}
	

}
