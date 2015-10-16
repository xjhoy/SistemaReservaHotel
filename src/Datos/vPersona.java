/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;


public class vPersona {
    private int idPersona;
    private String nombre;
    private String apellidoUno;
    private String apellidoDos;
    private String tipo_documento;
    private String num_documento;
    private String direccion;
    private String telefono;
    private String email;

    public vPersona() {
    }

    public vPersona(int idPersona, String nombre, String apellidoUno, String apellidoDos, String tipo_documento, String num_documento, String direccion, String telefono, String email) {
	this.idPersona = idPersona;
	this.nombre = nombre;
	this.apellidoUno = apellidoUno;
	this.apellidoDos = apellidoDos;
	this.tipo_documento = tipo_documento;
	this.num_documento = num_documento;
	this.direccion = direccion;
	this.telefono = telefono;
	this.email = email;
    }

    public int getIdPersona() {
	return idPersona;
    }

    public void setIdPersona(int idPersona) {
	this.idPersona = idPersona;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getApellidoUno() {
	return apellidoUno;
    }

    public void setApellidoUno(String apellidoUno) {
	this.apellidoUno = apellidoUno;
    }

    public String getApellidoDos() {
	return apellidoDos;
    }

    public void setApellidoDos(String apellidoDos) {
	this.apellidoDos = apellidoDos;
    }

    public String getTipo_documento() {
	return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
	this.tipo_documento = tipo_documento;
    }

    public String getNum_documento() {
	return num_documento;
    }

    public void setNum_documento(String num_documento) {
	this.num_documento = num_documento;
    }

    public String getDireccion() {
	return direccion;
    }

    public void setDireccion(String direccion) {
	this.direccion = direccion;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }
    
    
    
}
