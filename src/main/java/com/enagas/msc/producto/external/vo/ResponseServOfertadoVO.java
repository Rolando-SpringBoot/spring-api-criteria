package com.enagas.msc.producto.external.vo;

import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * La clase ResponseServOfertadoVO.
 * 
 */
@Data
public class ResponseServOfertadoVO {

	/**
	 * El idnServOfertado - Identificador interno del ServOfertado
	 */
	@JsonProperty(value = "idnServOfertado")
	@NotEmpty
	private Integer idnServOfertado;

	/**
	 * El idnServAtr - Código identificativo del ServAtr
	 */
	@JsonProperty(value = "idnServAtr")
	@NotEmpty
	private Integer idnServAtr;

	/**
	 * El indOfertado - Indicador de si un servicio está ofertado
	 */
	@JsonProperty(value = "indOfertado")
	@NotEmpty
	private Boolean indOfertado;

	/**
	 * El indSlot
	 */
	@JsonProperty(value = "indSlot")
	@NotEmpty
	private Boolean indSlot;

//    /**
//     * El audUsuAlta - Se llena la primera vez cuando se realiza el insert
//     */
//	 @JsonProperty(value = "audUsuAlta")
//	 @NotEmpty
//	 private String audUsuAlta;
//	 
//    /**
//     * El audFecAlta - Se llena la primera vez cuando se realiza el insert
//     */
//	 @JsonProperty(value = "audFecAlta")
//	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
//	 private java.sql.Timestamp audFecAlta;
//	 
//    /**
//     * El audUsuModi - Se llena cada vez que modificamos la fila
//     */
//	 @JsonProperty(value = "audUsuModi")
//	 private String audUsuModi;
//	 
//    /**
//     * El audFecModi - Se llena cada vez que modificamos la fila
//     */
//	 @JsonProperty(value = "audFecModi")
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
//	 private java.sql.Timestamp audFecModi;
//	 
//    /**
//     * El audFecBaja - Se llena cada vez que damos de baja la fila
//     */
//	 @JsonProperty(value = "audFecBaja")
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
//	 private java.sql.Timestamp audFecBaja;

	/**
	 * El indServLocalizado - Indicador de si un servicio está localizado
	 */
	@JsonProperty(value = "indServLocalizado")
	@NotEmpty
	private Boolean indServLocalizado;
}