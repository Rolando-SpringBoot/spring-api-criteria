package com.enagas.msc.producto.domain.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * The RequestProductoVO class.
 */
@Data
public class RequestFiltrosObtieneProductoVO {
	
	/**
     * The idnProducto - Identificador interno del producto
     */	
	private Integer idnProducto;
	
	 private List<Integer> idnListaProducto;
	 
	 private String codProducto;	 
	 
	 private Integer idnTipoProducto;	 

	 private List<Integer> idnServAtr;
	
	/**
	 * Selección del tipo de Producto
	 */
	private Boolean indEstadoProducto;
 
	 /**
	  * Lista de identificadores de la infraestructura (multiselección desde Front)
	  */
	 private List<Integer> idnInfr;
	 
	 /**
	  * Lista de identificadores de las Agrupaciones de Infraestructuras (multiselección desde Front)
	  */
	 private List<Integer> idnAgrupInfr;
	 
	 /**
	  * Lista de identificadores de las Puntos de Conexión (multiselección desde Front)
	  */
	 private List<Integer> idnPuntoConex;

    /**
     * The fecIniSesion - Fecha de inicio de sesión de negociación del producto
     */



	

	 //@NotNull(message = "Campo fecIniSesion obligatorio")



	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniSesion;
	 
    /**
     * The fecFinSesion - Fecha de fin de sesión de negociación del producto
     */
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinSesion;
	 
    /**
     * The fecIniProducto - Fecha de inicio del producto
     */




	 //@NotNull(message = "Campo fecIniProducto obligatorio")



	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniProducto;	
	 
    /**
     * The fecFinProducto - Fecha de fin del producto
     */
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinProducto;
	 
	 private transient LocalDateTime fecLimiteProgramada;
	 
	 private Boolean calcularPreciosyCap;
	 
}
