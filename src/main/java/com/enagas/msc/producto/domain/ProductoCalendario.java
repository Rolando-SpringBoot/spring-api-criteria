package com.enagas.msc.producto.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * The MscTproductoCalendario entity class.
 */
@Entity
@Data
@Table(name = "msc_tproducto_calendario")
public class ProductoCalendario implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificador del producto calendario
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idn_producto_calendario")
	private Integer idnProductoCalendario;

	/**
	 * Código identificativo del producto calendario
	 */
	@Column(name = "cod_producto_calendario")
	@NotEmpty
	private String codProductoCalendario;

	/**
	 * Identificador del tipo de producto
	 */
	@Column(name = "idn_tip_producto")
	@NotNull
	private Integer idnTipoProducto;

	/**
	 * Identificador del servicio
	 */
	@Column(name = "idn_serv_atr")
	@NotNull
	private Integer idnServAtr;

	/**
	 * Fecha de inicio estándar para el Producto y Servicio
	 */
	@Column(name = "fec_ini_estandar")
	@NotNull
	private LocalDateTime fecIniEstandar;

	/**
	 * Fecha de Fin estándar para el Producto y Servicio
	 */
	@Column(name = "fec_fin_estandar")
	@NotNull
	private LocalDateTime fecFinEstandar;

	/**
	 * Fecha de Inicio del producto administrado
	 */
	@Column(name = "fec_ini_vigencia")
	@NotNull
	private LocalDateTime fecIniVigencia;

	/**
	 * Fecha de Fin del producto administrado
	 */
	@Column(name = "fec_fin_vigencia")
	private LocalDateTime fecFinVigencia;

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

}