package com.enagas.msc.producto.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * The MscTproducto entity class.
 */
@Entity
@Data
@Table(name = "msc_tproducto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificador interno del producto
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idn_producto")
	private Integer idnProducto;

	/**
	 * Código identificativo del producto
	 */
	@Column(name = "cod_producto")
	@NotEmpty
	private String codProducto;

	/**
	 * Tipo de Producto
	 */
	@Column(name = "idn_tip_producto")
	@NotNull
	private Integer idnTipoProducto;

	/**
	 * Servicio al que se asocia el producto
	 */
	@Column(name = "idn_serv_atr")
//	@NotNull
	private Integer idnServAtr;

	/**
	 * Identificador de la infraestructura 
	 */
	@Column(name = "idn_infr")
//	@NotNull
	private Integer idnInfr;

	/**
	 * Identificador de la agrupacion de la infraestructura
	 */
	@Column(name = "idn_agrup_infr")
//	@NotNull
	private Integer idnAgrupInfr;
	
	@Column(name = "idn_unidad_medida")
//	@NotNull
	private Integer idnUnidadMedida;
	
	@Column(name = "idn_unidad_precio")
//	@NotNull
	private Integer idnUnidadPrecio;

	/**
	 * Identificador del punto de conexion
	 */
	@Column(name = "idn_punto_conex")
//	@NotNull
	private Integer idnPuntoConex;

	/**
	 * Fecha de inicio del producto
	 */
	@Column(name = "fec_ini_producto")
	@NotNull
	private LocalDateTime fecIniProducto;

	/**
	 * Fecha de fin del producto
	 */
	@Column(name = "fec_fin_producto")
	@NotNull
	private LocalDateTime fecFinProducto;

	/**
	 * Fecha de inicio de sesión de negociación del producto
	 */
	@Column(name = "fec_ini_sesion")
	@NotNull
	private LocalDateTime fecIniSesion;

	/**
	 * Fecha de fin de sesión de negociación del producto
	 */
	@Column(name = "fec_fin_sesion")
	@NotNull
	private LocalDateTime fecFinSesion;

	/**
	 * Versión del producto
	 */
	@Column(name = "num_version")
	@NotNull
	private Integer numVersion;


	/**
	 * Fecha de alta de la versión
	 */
	@Column(name = "fec_version")
	@NotNull
	private LocalDateTime fecVersion;
	
	/**
	 * capacidad maxima de compra
	 */
	@Column(name = "max_val_capacidad")
	private Long maxCapacidadCompra;
	
	/**
	 * precio maximo de compra
	 */
	@Column(name = "max_val_precio")
	private java.math.BigDecimal maxPrecioCompra;
	
	/**
	 * capacidad minima de venta
	 */
	@Column(name = "min_val_capacidad")
	private Long minCapacidadVenta;
	
	/**
	 * precio minimo de venta
	 */
	@Column(name = "min_val_precio")
	private java.math.BigDecimal minPrecioVenta;
	/**
	 * Se llena la primera vez cuando se realiza el insert
	 */
	@Column(name = "aud_usu_alta")
	@NotEmpty
	private String audUsuAlta;

	/**
	 * Se llena la primera vez cuando se realiza el insert
	 */
	
	@CreationTimestamp
	@Column(name = "aud_fec_alta", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime audFecAlta;

	/**
	 * Se llena cada vez que modificamos la fila
	 */
	@Column(name = "aud_usu_modi")
	private String audUsuModi;

	/**
	 * Se llena cada vez que modificamos la fila
	 */
	@Column(name = "aud_fec_modi")
	private LocalDateTime audFecModi;

	/**
	 * Se llena cada vez que damos de baja la fila
	 */
	@Column(name = "aud_fec_baja")
	private LocalDateTime audFecBaja;

	/**
	 * Asociacion OneToMany bidireccional a ProductoVer
	 */
	@OneToMany(mappedBy = "idnProducto")
	private List<ProductoVer> productoVer;

}