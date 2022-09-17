package com.enagas.msc.producto.external.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsultarOfertaVO {

	/**
	 * The idnOferta
	 */
	@JsonProperty(value = "idnOferta")
	private Integer idnOferta;

	/**
	 * The codOferta
	 */
	@JsonProperty(value = "codOferta")
	private String codOferta;

	/**
	 * The idnProducto
	 */
	@JsonProperty(value = "idnProducto")
	private Integer idnProducto;

	/**
	 * The idnSujeto
	 */
	@JsonProperty(value = "idnSujeto")
	private Integer idnSujeto;

	/**
	 * Código de la instalación
	 */
	@JsonProperty(value = "codInstalacion")
	private String codInstalacion;

	/**
	 * Descripción completa de la instalacion (código más descripción)
	 */
	@JsonProperty(value = "instalacion")
	private String instalacion;
	
	/**
	 * Identificador de la infraestructura de la oferta
	 */
	@JsonProperty(value = "idnInfr")
	private Integer idnInfr;

	/**
	 * Descripción del agente de mercado
	 */
	@JsonProperty(value = "agenteMercado")
	private String agenteMercado;

	/**
	 * The indTipoOferta
	 */
	@JsonProperty(value = "indTipoOferta")
	private boolean indTipoOferta;

	/**
	 * mesAnio de la oferta
	 */
	@JsonProperty(value = "mesAnio")
	private String mesAnio;

	/**
	 * The codCto
	 */
	@JsonProperty(value = "codCto")
	private String codCto;

	/**
	 * Cantidad de capacidad de la oferta
	 */
	@JsonProperty(value = "valCapacidad")
	private Long valCapacidad;

	/**
	 * Fecha original de Slot
	 */

	@JsonProperty(value = "fecSlotOriginal")
	private LocalDateTime fecSlotOriginal;

	@JsonProperty(value = "fecSlot")
	private LocalDateTime fecSlot;

	/**
	 * Precio de la oferta
	 */
	@JsonProperty(value = "valPrecio")
	private java.math.BigDecimal valPrecio;

	@JsonProperty(value = "fecVencimiento")
	private LocalDateTime fecVencimiento;

	/**
	 * The idnEstadoEntidad
	 */
	@JsonProperty(value = "idnEstadoEntidad")
	private Integer idnEstadoEntidad;

	/**
	 * The txtObsOferta
	 */
	@JsonProperty(value = "txtObsOferta")
	private String txtObsOferta;

	/**
	 * The indGarantiaRetenida
	 */
	@JsonProperty(value = "indGarantiaRetenida")
	private boolean indGarantiaRetenida;

	/**
	 * The indGts
	 */
	@JsonProperty(value = "indGts")
	private boolean indGts;

	/**
	 * The indAllNone
	 */
	@JsonProperty(value = "indAllNone")
	private boolean indAllNone;

	/**
	 * Indica si la oferta tiene indPrecasacion
	 */
	@JsonProperty(value = "indPrecasacion")
	private boolean indPrecasacion;

	/**
	 * Indica si la oferta viene de un fichero
	 */
	@JsonProperty(value = "indFichero")
	private boolean indFichero;

	/**
	 * The numVersion
	 */
	@JsonProperty(value = "numVersion")
	private Long numVersion;

	/**
	 * The fecVersion
	 */
	@JsonProperty(value = "fecVersion")
	private LocalDateTime fecVersion;

	/**
	 * The origen
	 */
	@JsonProperty(value = "origen")
	private Integer origen;

	/**
	 * The precio prima contrato
	 */
	@JsonProperty(value = "precPrimaContrato")
	private Integer precPrimaContrato;

	/**
	 * The audFecModi - Se llena cada vez que modificamos la fila
	 */
	@JsonProperty(value = "audFecModi")
	private LocalDateTime audFecModi;

	@JsonProperty(value = "numMod")
	private Integer numMod;

	/**
	 * The audFecBaja - Se llena cada vez que damos de baja la fila
	 */
	@JsonProperty(value = "audFecBaja")
	private LocalDateTime audFecBaja;

}
