package com.enagas.msc.producto.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * The MscTproductoVer entity class.
 */
@Entity
@Data
@Table(name = "msc_tproducto_ver")
public class ProductoVer implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificador interno de producto versión
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idn_producto_ver")
	private Integer idnProductoVer;

	/**
	 * Código identificativo de producto versión
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
	 * Fecha de fin de sesión de negociacion del producto
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
	
//	/**
//	 * capacidad maxima de compra
//	 */
//	@Column(name = "max_capacidad_compra")
//	private BigDecimal maxCapacidadCompra;
//	
//	/**
//	 * precio maximo de compra
//	 */
//	@Column(name = "max_precio_compra")
//	private BigDecimal maxPrecioCompra;
//	
//	/**
//	 * capacidad minima de venta
//	 */
//	@Column(name = "min_capacidad_venta")
//	private BigDecimal minCapacidadVenta;
//	
//	/**
//	 * precio minimo de venta
//	 */
//	@Column(name = "min_precio_venta")
//	private BigDecimal minPrecioVenta;

	/**
	 * Se llena la primera vez cuando se realiza el insert
	 */
	@Column(name = "aud_usu_alta")
	@NotEmpty
	private String audUsuAlta;

	/**
	 * Se llena la primera vez cuando se realiza el insert
	 */
	@Column(name = "aud_fec_alta")
	@NotNull
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
	 * Asociacion ManyToOne bidireccional a MscTproducto
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idnProducto")
	private Producto idnProducto;

}