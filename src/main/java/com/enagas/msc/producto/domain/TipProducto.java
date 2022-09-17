package com.enagas.msc.producto.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The MscTtipProducto entity class.
 */
@Data
@Entity
@Table(name = "msc_ttip_producto")
public class TipProducto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificador interno del tipo de producto
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idn_tip_producto")
	private Integer idnTipoProducto;

	/**
	 * Nombre del tipo de producto
	 */
	@EqualsAndHashCode.Exclude
	@NotBlank(message = "Campo desTipoProducto obligatorio")
	@Column(name = "des_tipo_producto", nullable = false)
	private String desTipoProducto;

	/**
	 * Abreviatura del tipo producto de un solo carácter
	 */
	@EqualsAndHashCode.Exclude
	@NotBlank(message = "Campo txtAbrvProducto obligatorio")
	@Column(name = "txt_abrv_producto", nullable = false, length = 1)
	private String txtAbrvProducto;

	/**
	 * Número de orden del tipo producto
	 */
	@EqualsAndHashCode.Exclude
	@NotNull(message = "Campo numOrden obligatorio")
	@Column(name = "num_orden", nullable = false)
	private Integer numOrden;

	/**
	 * Se llena la primera vez cuando se realiza el insert
	 */
	@EqualsAndHashCode.Exclude
	@NotBlank(message = "Campo audUsuAlta obligatorio")
	@Column(name = "aud_usu_alta", nullable = false, length = 12)
	private String audUsuAlta;

	/**
	 * Se llena la primera vez cuando se realiza el insert
	 */
	@EqualsAndHashCode.Exclude
	@Column(name = "aud_fec_alta", nullable = true, columnDefinition = "default current_timestamp")
	private LocalDateTime audFecAlta;

	/**
	 * Se llena cada vez que modificamos la fila
	 */
	@EqualsAndHashCode.Exclude
	@Column(name = "aud_usu_modi", nullable = true, length = 12)
	private String audUsuModi;

	/**
	 * Se llena cada vez que modificamos la fila
	 */
	@EqualsAndHashCode.Exclude
	@Column(name = "aud_fec_modi", nullable = true)
	private LocalDateTime audFecModi;

	/**
	 * Se llena cada vez que damos de baja la fila
	 */
	@EqualsAndHashCode.Exclude
	@Column(name = "audFecBaja", nullable = true)
	private LocalDateTime audFecBaja;

}
