package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhoseph Arango
 */
public class conexion {
    
    //Definimos las variables que vamos a usar para conectar la base de datos
    public String DB = "baseReserva";
    public String url = "jdbc:mysql://127.0.0.1/"+DB;
    public String user = "root";
    public String pass = "";

    //Creamos un constructor vacio
    public conexion() {
    
    }
    
    //Ahora hacemos un metodo llamado conectar del tipo Connection
    public Connection conectar(){
	
	//Creamos una variable llamada link del tipo Connection y inicializaremos en null
	Connection link = null;
	
	//Dentro de un try/catch  haremos la conexión 
	try {
	    //Primero diremos donde esta el Driver que hará la conexión
	    Class.forName("org.gjt.mm.mysql.Driver");
	    
	    //Con la variable llamada link haremos la conexion pasandole las variables public creadas
	    link = DriverManager.getConnection(this.url, this.user, this.pass);
	    
	} catch (ClassNotFoundException | SQLException e) {
	    JOptionPane.showConfirmDialog(null,e);
	}
	return link;
    }
    
}
