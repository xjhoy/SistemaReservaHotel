/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vCliente;
import Datos.vProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Jhoseph Arango
 */
public class fCliente {
    // Creamos variables para la conexion
    
    // Creamos una variable para que maneje la clase conexion y
    // se conecte a la base de datos
    private conexion mysql = new conexion();
    
    // Creamos una variable tipo Connection para inicializarla
    // con la variable anterior y usar el metodo conectar
    private Connection cn = mysql.conectar();
    
    // Creamos una variable para la sentencia SQL
    private String sSQL = "";
    private String sSQL2 = "";
    
    // Variable que mostrara la cuenta de los registros
    public Integer totalregistros;
    
    
    // Muestra los registros, se necesita un String
    public DefaultTableModel mostrar(String buscar){
        
        // DefaultTableModel asigna los datos que estan en la tabla, creamos el modelo
        DefaultTableModel modelo;
        // Usamos un array tipo String que guardará los TITULOS
        String[] titulos ={"ID","Nombre","1ª Apellido","2ª Apellido","Doc","nº Doc","Direccion","Telefono","email","nº Cliente"};
        // Ahorra un array vacio para los registros
        String[] registro = new String[10];
        
        // Inicializamos en 0
        totalregistros = 0;
        
        // De esta forma definimos la cabecera y los registros seran agregados en filas
        modelo = new DefaultTableModel(null,titulos);
        
        // Definimos la variable sSQL con la sentencia SELECT esta sentencia selecciona 2 tablas con relacion
        // tabla tbPersona y tbCliente con sus campos idPersona
        sSQL = "SELECT p.idPersona, p.nombre, p.apellidoUno, p.apellidoDos, p.tipo_documento, p.num_documento," +
                "p.direccion, p.telefono, p.email, c.cod_cliente " +
                "FROM tbpersona p INNER JOIN tbcliente c ON p.idpersona = c.idpersona "+
                "WHERE num_documento LIKE '%" + buscar + "%' ORDER BY idpersona DESC";
        
        try {
            // Hay que crear un estamento para luego parar a un resulSet
            // cn es de tipo Connection
            Statement st = cn.createStatement();
            // Creamos Resulset y lo inicializamos con la ejecucion de los parametros de la sentencia
            ResultSet rs = st.executeQuery(sSQL);
            
            /*****************************************************************************
	     * Donde rs o la variable ResulSet seran lo mismos que el recorSet en basic  *
	     *****************************************************************************/
	    
            // Mientras se pueda dar siguiente al rs vamos a agregar al array registros los datos de la
            // tabla tbHabitacion
            while (rs.next()) {
                // getString("registro"); donde el registro tiene que ir escrito igual que en la base de datos
                registro [0] = rs.getString("idPersona");
                registro [1] = rs.getString("nombre");
                registro [2] = rs.getString("apellidoUno");
                registro [3] = rs.getString("apellidoDos");
                registro [4] = rs.getString("tipo_documento");
                registro [5] = rs.getString("num_documento");
                registro [6] = rs.getString("direccion");
                registro [7] = rs.getString("telefono");
                registro [8] = rs.getString("email");
                registro [9] = rs.getString("cod_cliente");
                
                // Aumentamos el contador
                totalregistros = totalregistros + 1;
                
                // agregamos la array a la tabla
                modelo.addRow(registro);
                
            } // fin while
            
            // Esto en verdad regresa los datos
            return modelo;
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        } // fin try-catch
            
    } // end mostrar
    
    public boolean insertar(vCliente dts){
        
        sSQL = "INSERT INTO tbPersona" +
                "(nombre, apellidoUno, apellidoDos, tipo_documento, num_documento, direccion, telefono , email)"+
                "VALUES(?,?,?,?,?,?,?,?)";
        sSQL2 = "INSERT INTO tbCliente(idPersona, cod_cliente)"+
                "VALUES((SELECT idPersona FROM tbPersona ORDER BY idPersona DESC LIMIT 1),?)";
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellidoUno());
            pst.setString(3, dts.getApellidoDos());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());
            
            pst2.setString(1, dts.getCod_cliente());
            
            int n  = pst.executeUpdate();
            
            if (n != 0) {
                
                int n2 = pst2.executeUpdate();
                
                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
                
            } else {
                return false;
            }
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean editar(vCliente dts){
        
        // Pasamos la sentencia SQL para editar las tablas tbPersona y tbCliente
        // Se editar en orden para no afectar las tablas y su relacion.
        sSQL = "UPDATE tbpersona"+
                " SET nombre=?, apellidoUno=?, apellidoDos=?, tipo_documento=?, num_documento=?,"+
                "direccion=?, telefono =?, email=? WHERE idpersona=?";
        
        sSQL2 = "UPDATE tbcliente SET cod_cliente=? WHERE idpersona=?";
        
        
        try {
            // Preparamos el preparedStatement de cada sentencia.
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            // pasamos los cada variable en orden
            // tbPersona
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellidoUno());
            pst.setString(3, dts.getApellidoDos());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());
            pst.setInt(9, dts.getIdPersona());
            
            // tbCliente
            pst2.setString(1, dts.getCod_cliente());
            pst2.setInt(2, dts.getIdPersona());
            
            // EJECUTAMOS LA PRIMER SENTENCIA
            int n  = pst.executeUpdate();
            
            // si se ejecuta la sentencia con un valor 
            // Se EJECUTARA LA SEGUNDA SENTENCIA
            if (n != 0) {
                
                int n2 = pst2.executeUpdate();
                
                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
                
            } else {
                return false;
            }
            
            
        } catch (Exception e) {
            // mensaje de error
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }// fin editar
    
    public boolean eliminar(vCliente dts){
        
       // borrar primero el registro en la tabla tbCliente que
       // es la tabla de menor rango y luego se borra la tabla tbPersona
       sSQL= "DELETE FROM tbCliente WHERE idpersona = ?";
       sSQL2= "DELETE FROM tbPersonas WHERE idpersona = ?";
       
        try {
            
            // Preparamos los preparedStament de las 2 sentencias
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL);
            
            // pasamos las variables al pst y a pst2
            pst.setInt(1, dts.getIdPersona());
            pst2.setInt(1, dts.getIdPersona());
            
            
            
            int n = pst.executeUpdate();
            
            // si se ejecuta la sentencia con un valor 
            // Se EJECUTARA LA SEGUNDA SENTENCIA
            if (n != 0) {
                
                int n2 = pst2.executeUpdate();
                
                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
                
            } else {
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
            
        }
        
    }
    
    
}
