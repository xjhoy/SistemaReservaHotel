/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Jhoseph Arango
 */
public class vProducto {
   private int idProducto;
   private String nombre;
   private String descripcion;
   private String unidad_medida;
   private Double precio_venta;

    public vProducto() {
    }

    public vProducto(int idProducto, String nombre, String descripcion, String unidad_medida, Double precio_venta) {
	this.idProducto = idProducto;
	this.nombre = nombre;
	this.descripcion = descripcion;
	this.unidad_medida = unidad_medida;
	this.precio_venta = precio_venta;
    }

    public int getIdProducto() {
	return idProducto;
    }

    public void setIdProducto(int idProducto) {
	this.idProducto = idProducto;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getUnidad_medida() {
	return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
	this.unidad_medida = unidad_medida;
    }

    public Double getPrecio_venta() {
	return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
	this.precio_venta = precio_venta;
    }
   
   
}
