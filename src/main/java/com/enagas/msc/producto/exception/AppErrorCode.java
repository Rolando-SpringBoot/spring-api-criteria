package com.enagas.msc.producto.exception;

import com.enagas.arch.core.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration of error codes.
 */
@AllArgsConstructor
@Getter
public enum AppErrorCode implements ErrorCode {

	/** The busi 001. */
	BUSI_001("La fecha de inicio del producto no es válida para el tipo de producto y servicio seleccionados."),
	BUSI_002("La fecha de fin del producto no es válida para el tipo de producto y servicio seleccionados."),
	BUSI_003("La fecha/hora de inicio de sesión de negociación debe ser posterior al día actual y anterior a la fecha de inicio del producto informado."),
	BUSI_004("La fecha/hora de fin de sesión de negociación debe ser igual o posterior a la fecha/hora de inicio de sesión de negociación informada y anterior a la fecha de inicio del producto."),
	BUSI_005("Ya existe un registro para el mismo tipo de producto, servicio, instalación y fechas seleccionadas."),
	BUSI_006("No es posible modificar un producto con periodo de sesión cerrado"),
	BUSI_007("No es posible modificar un registro con ofertas vinculadas."),
	BUSI_008("No se permite borrar productos con periodo de negociación cerrado"),
	BUSI_009("No se permite borrar productos con ofertas vinculadas"),
	BUSI_010("La fecha de fin de producto debe ser igual o posterior a la fecha de inicio"),
	BUSI_011("El campo instalación es obligatorio"),
	BUSI_012("Debe ingresa al menos un identificador del producto"),
	BUSI_013("La fecha de inicio/fin de producto no es válida para el tipo de producto y servicio seleccionado"),
	BUSI_014("La fecha/hora de fin de sesión de negociación debe ser igual o posterior a la fecha/hora de inicio de sesión de negociación informada y anterior o igual a la fecha de fin del producto"),
	//BUSI_013("La cantidad de registros supera más de "),
	TECH_001("Ha ocurrido un error durante el procesamiento de la información"),
	//TECH_002("Error en procesamiento de datos");
	BUSI_015("No se ha creado ningún producto. Ya existe algún registro para el mismo tipo de producto, servicio, instalación y fechas seleccionadas: Producto: "),
	BUSI_016(" es el código de producto."),
	BUSI_017("La acción no se ha podido realizar. Su usuario no está habilitado para acceder a la Plataforma"),
	BUSI_018("La acción no se ha podido realizar. Su usuario solo está habilitado para acceder con permisos de consulta a la Plataforma");


	private String reasonPhrase;

	// Methods -----------------------------------------------------------------
	/**
	 * Metodo que busca un enumerado por su nombre
	 * @param name
	 * @return
	 */
	public static AppErrorCode findByName(String name) {
		AppErrorCode errorCode = null;
		for (AppErrorCode eCode : values()) {
			if (eCode.name().equals(name)) {
				errorCode = eCode;
			}
		}
		return errorCode;
	}

	/**
	 * Cambia la descricion del error
	 * @param reason Nueva descripcion
	 */
	public void changeReason(String reason) {
		reasonPhrase = reason;
	}


}
