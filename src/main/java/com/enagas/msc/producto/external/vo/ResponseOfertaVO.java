package com.enagas.msc.producto.external.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The ResponseOfertaVO class.
 */
@Data
public class ResponseOfertaVO {

    /**
     * The idnOferta - Identificador interno de la oferta
     */
	 @JsonProperty(value = "idnOferta")
	 @NotEmpty
	 private Integer idnOferta;
	 
    /**
     * The codOferta - Código identificativo de la oferta
     */
	 @JsonProperty(value = "codOferta")
	 @NotEmpty
	 private String codOferta;
	 
    /**
     * The idnProducto - Producto al que se asocia la oferta
     */
	 @JsonProperty(value = "idnProducto")
	 @NotEmpty
	 private Integer idnProducto;
	 
    /**
     * The idnSujeto - Agente de Mercado que ha creado la oferta
     */
	 @JsonProperty(value = "idnSujeto")
	 @NotEmpty
	 private Integer idnSujeto;
	 
    /**
     * The indTipoOferta - tipo de oferta (venta / compra)
     */
	 @JsonProperty(value = "indTipoOferta")
	 @NotEmpty
	 private boolean indTipoOferta;
	 
    /**
     * The codCto - Código de contrato para las ofertas de venta
     */
	 @JsonProperty(value = "codCto")
	 private String codCto;
	 
    /**
     * The valCapacidad - Cantidad de capacidad de la oferta
     */
	 @JsonProperty(value = "valCapacidad")
	 @NotEmpty
	 private Long valCapacidad;
	 
    /**
     * The valPrecio - Precio de la oferta
     */
	 @JsonProperty(value = "valPrecio")
	 @NotEmpty
	 private java.math.BigDecimal valPrecio;
	 
    /**
     * The fecCreacion - Fecha de creación de la oferta
     */
	 @JsonProperty(value = "fecCreacion")
	 @NotEmpty
	 private LocalDateTime fecCreacion;
	 
    /**
     * The fecModificacion - Fecha de última modificación de la oferta
     */
	 @JsonProperty(value = "fecModificacion")
	 @NotEmpty
	 private LocalDateTime fecModificacion;
	 
    /**
     * The fecVencimiento - Fecha / hora máxima para poder casar la oferta
     */
	 @JsonProperty(value = "fecVencimiento")
	 private LocalDateTime fecVencimiento;
	 
    /**
     * The idnEstadoEntidad - Identificador del estado de la oferta (Enviada/Casada/Cancelada/Expirada)
     */
	 @JsonProperty(value = "idnEstadoEntidad")
	 @NotEmpty
	 private Integer idnEstadoEntidad;
	 
    /**
     * The txtObsOferta - Referencia, motivo rechazo de tabla de garantías
     */
	 @JsonProperty(value = "txtObsOferta")
	 private String txtObsOferta;
	 
    /**
     * The indGarantiaRetenida - Indica si la oferta tiene garantías retenidas
     */
	 @JsonProperty(value = "indGarantiaRetenida")
	 @NotEmpty
	 private java.math.BigDecimal indGarantiaRetenida;
	 
    /**
     * The indGts - Indica si la versión de la oferta ha sido creada por GTS en nombre del agente
     */
	 @JsonProperty(value = "indGts")
	 @NotEmpty
	 private java.math.BigDecimal indGts;
	 
    /**
     * The indAllNone - Indica si permite casaciones parciales o no
     */
	 @JsonProperty(value = "indAllNone")
	 @NotEmpty
	 private boolean indAllNone;
	 
    /**
     * The numVersion - Versión de la oferta
     */
	 @JsonProperty(value = "numVersion")
	 @NotEmpty
	 private Integer numVersion;
	 
    /**
     * The fecVersion - Fecha de alta de la versión
     */
	 @JsonProperty(value = "fecVersion")
	 @NotEmpty
	 private LocalDateTime fecVersion;
	 
    /**
     * The origen - Indica si la versión de la oferta la ha creado un usuario o el sistema de forma automática (ofertas de resto de casación)
     */
	 @JsonProperty(value = "origen")
	 @NotEmpty
	 private Long origen;
	 
	 /**
     * The indCasacion - Indica si esta en casación
     */
	 @JsonProperty(value = "indCasacion")
	 @NotEmpty
	 private boolean indCasacion;
	 
	 /**
     * The orden - 
     */
	 @JsonProperty(value = "orden")
	 private Integer orden;

    /**
     * The audUsuAlta - Se llena la primera vez cuando se realiza el insert
     */
	 @JsonProperty(value = "audUsuAlta")
	 @NotEmpty
	 private String audUsuAlta;
	 
    /**
     * The audFecAlta - Se llena la primera vez cuando se realiza el insert
     */
	 @JsonProperty(value = "audFecAlta")
	 @NotEmpty
	 private LocalDateTime audFecAlta;
	 
    /**
     * The audUsuModi - Se llena cada vez que modificamos la fila
     */
	 @JsonProperty(value = "audUsuModi")
	 private String audUsuModi;
	 
    /**
     * The audFecModi - Se llena cada vez que modificamos la fila
     */
	 @JsonProperty(value = "audFecModi")
	 private LocalDateTime audFecModi;
	 
    /**
     * The audFecBaja - Se llena cada vez que damos de baja la fila
     */
	 @JsonProperty(value = "audFecBaja")
	 private LocalDateTime audFecBaja; 
	 
	 @JsonProperty(value = "idnInfrOferta")
	 private Integer idnInfr;
	 
	 /**
	  * Campo visible solo para ofertas cuyo servicio = Descarga de buques
	  */
	 @JsonProperty(value = "fecSlot")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecSlot;
		
	 /**
	  * Campo visible solo para ofertas cuyo servicio = Descarga de buques
	  */
	 @JsonProperty(value = "fecSlotOriginal")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecSlotOriginal;
		
	 /**
	  * Campo relacionado con ofertas de descarga de buques
	  */
	 @JsonProperty(value ="numMod" )
	 private Integer numMod;
	 
	 @JsonProperty(value ="esSlotSinFecha" )
	 private boolean esSlotSinFecha;
	 
	 @JsonProperty(value ="indOfertaCompatible")
	 private Boolean indOfertaCompatible;
	 
	 @JsonProperty(value="codEstado")
	 private String codEstado;
	 
}