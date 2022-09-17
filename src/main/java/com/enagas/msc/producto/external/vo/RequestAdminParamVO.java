package com.enagas.msc.producto.external.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestAdminParamVO {
	
	@JsonProperty(value = "lstNomParametros")
	private List<String> lstNomParametros;
	
	/**
     * The idnAdminParam - Identificador interno del parámetro de administración
     */
	 @JsonProperty(value = "idnAdminParam")
	 private Integer idnAdminParam;
	 
	 /**
     * The idnServAtr - 
     */
	 @JsonProperty(value = "idnServAtr")
	 @NotNull(message = "Campo idnServAtr obligatorio")
	 private Integer idnServAtr;
	 
	 /**
     * The idnTipProducto - 
     */
	 @JsonProperty(value = "idnTipProducto")
	 @NotNull(message = "Campo idnTipProducto obligatorio")
	 private Integer idnTipProducto;
	 
	 /**
     * The idnInstalacion - 
     */
	 @JsonProperty(value = "idnInfr")
	 private Integer idnInfr;
	 
	 /**
     * The idnTipInstalacion - 
     */
	 @JsonProperty(value = "idnAgrupInfr")
	 private Integer idnAgrupInfr;
	 
	 /**
     * The idnTipInstalacion - 
     */
	 @JsonProperty(value = "idnPuntoConex")
	 private Integer idnPuntoConex;
	 
	 /**
     * The audFecAlta - Se llena la primera vez cuando se realiza el insert
     */
	 @JsonProperty(value = "fecIniVigencia")
	 @NotNull(message = "Campo fecIniVigencia obligatorio")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniVigencia;	 
	
    /**
     * The audFecModi - Se llena cada vez que modificamos la fila
     */
	 @JsonProperty(value = "fecFinVigencia")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinVigencia;	

}
