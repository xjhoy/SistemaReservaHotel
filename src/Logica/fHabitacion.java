package Logica;

import Datos.vHabitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**************************
 * @author Jhoseph Arango *
 **************************/

public class fHabitacion {
    
//Creamos variables para la conexion 
    
    //Creamos una variable para que maneje la clase conexión y se conecte a la base de datos
    private conexion mysql = new conexion();
    //Creamos una variable tipo Connection para inicializarla con la variable anterior y usar el metodo conectar
    private Connection cn = mysql.conectar();
    //Creamos una variable para la sentencia SQL
    private String sSQL = "";
    //Variable que mostrara la cuenta de los registros
    public Integer totalregistros;
    
    
    //Mustra los registros, se necesita un string
    public DefaultTableModel mostrar(String buscar){
	
	//DefaulTableModel asigna los datos que estaran en la tabla, creamos el modelo
	DefaultTableModel modelo;
	//Usamos un array tipo String que guardará los TITULOS
	String[] titulos = {"ID","Numero","Piso","Descripcion","Caracteristicas","Precio","Estado","Tipo habitacion"};
	//Ahorra un array vacio para los registros 
	String[] registro = new String[8];
	
	//Inicializamos en 0 
	totalregistros = 0;
	
	//De esta forma definimos la cabecera  y los registros seran agregados en filas 
	modelo = new DefaultTableModel(null,titulos);
	
	//Definimos la  variable sSQL con la sentencia SELECT y en orden
	sSQL = "SELECT * FROM tbHabitacion WHERE pisoHab like '%" + buscar + "%' ORDER BY idHab";
	
	try {
	    //Hay que crear un estamento para luego parar a un resulSet
	    //cn es de tipo Connection
	    Statement st = cn.createStatement();
	    //Creamos ResulSet y lo inicializamos con la ejecucion de los parametros de la sentencia
	    ResultSet rs = st.executeQuery(sSQL);
	    
	    /*****************************************************************************
	     * Donde rs o la variable ResulSet seran lo mismos que el recorSet en basic  *
	     *****************************************************************************/
	    
	    //Mientras se pueda dar siguiente al rs vamos a agregar al array registros los datos de la tabla tbHabitacion
	    while(rs.next()){
		//getString("registro"); donde el registro tiene que ir escrito igual que en la base de datos 
		registro [0] = rs.getString("idHab");
		registro [1] = rs.getString("numHab");
		registro [2] = rs.getString("pisoHab");
		registro [3] = rs.getString("descripcionHab");
		registro [4] = rs.getString("caracteristicasHab");
		registro [5] = rs.getString("precio_diarioHab");
		registro [6] = rs.getString("estadoHab");
		registro [7] = rs.getString("tipoHab");
		
		//Aumentamos el contador 
		totalregistros = totalregistros + 1;
		
		//agregamos la array a la tabla 
		modelo.addRow(registro);
		
		
		
	    }
	    //Esto en verdad regresa las datos 
	    return modelo;
	    
	} catch (Exception e) {
	    JOptionPane.showConfirmDialog(null, e);
	    return null;
	}
	
    }//End metodo mostrar
    
    
    //Metodo Insertar requiere un argumento de tipo vHabitacion, dts
    //La clase vHabitacion contiene solo las variables de los registros de Habitacion con en get y el set
    public boolean insertar(vHabitacion dts){
	    
	//Inicializamos la variable String sSQL con el estamento INSERT INTO 
	sSQL = "INSERT INTO tbHabitacion (numHab,pisoHab,descripcionHab,caracteristicasHab,precio_diarioHab,estadoHab,tipoHab)" +
			    "VALUES (?,?,?,?,?,?,?)";
	
	
	try {
	    //Usamos un objeto PreparedStatement llamado pst y lo inicializaremos con el objeto ya creado cn de tipo connection  y de argumento el string sSQL
	    PreparedStatement pst = cn.prepareStatement(sSQL);
	    
	    //pst guarda un array del numero de signos ? que hayamos puestos en sSQL
	    //dts sera un objeto que le pasaremos por argumento de tipo vHabitacion
	    pst.setString(1, dts.getNumero());
	    pst.setString(2, dts.getPiso());
	    pst.setString(3, dts.getDescripcion());
	    pst.setString(4, dts.getCaracteristicas());
	    pst.setDouble(5, dts.getPrecio_diario());
	    pst.setString(6, dts.getEstado());
	    pst.setString(7, dts.getTipo_habitacion());
	    
	    //Creamos una variable int y lo inicializamos con el metodo pst.executeUpdate()
	    int n = pst.executeUpdate();
	    
	    //Hacemos una comprobacion donde si la variable n de tipo int es diferente a 0 se habra hecho la instruccion
	    if(n != 0){
		return true;
	    }
	    else{
		return false;
	    }
	    
	} catch (Exception e) {
	    JOptionPane.showConfirmDialog(null, e);
	    return false;
	}
			
    }
    
    public boolean editar(vHabitacion dts){
	//Para hacer el preparedStatement para pasar el valor de los registros pondremos =?    
	sSQL = "UPDATE tbHabitacion SET numHab=?,pisoHab=?,descripcionHab=?,caracteristicasHab=?,precio_diarioHab=?,estadoHab=?,tipoHab=?"+
		    "WHERE idHab = ?";// este ? sera el array numero 8
	    
	try {
	    PreparedStatement pst = cn.prepareStatement(sSQL);
	    pst.setString(1, dts.getNumero());
	    pst.setString(2, dts.getPiso());
	    pst.setString(3, dts.getDescripcion());
	    pst.setString(4, dts.getCaracteristicas());
	    pst.setDouble(5, dts.getPrecio_diario());
	    pst.setString(6, dts.getEstado());
	    pst.setString(7, dts.getTipo_habitacion());
	    pst.setInt(8, dts.getIdHabitacion());
	    
	    int n = pst.executeUpdate();
	    if(n != 0){
		return true;
	    }
	    else{
		return false;
	    }
	    
	} catch (Exception e) {
	    JOptionPane.showConfirmDialog(null, e);
	    return false;
	}
			
    }
    
    public boolean eliminar (vHabitacion dts){
	//Solo daremos 1 argumento
	sSQL= "DELETE FROM tbHabitacion WHERE idHab=?";
	
	try {
	    
	    PreparedStatement pst = cn.prepareStatement(sSQL);
	    pst.setInt(1, dts.getIdHabitacion());
	    
	    int n = pst.executeUpdate();
	    
	    if(n != 0){
		return true;
	    }
	    else{
		return false;
	    }
	    
	} catch (Exception e) {
	    JOptionPane.showConfirmDialog(null, e);
	    return false;
	}
			
    }    
}//End class fHabitacion



