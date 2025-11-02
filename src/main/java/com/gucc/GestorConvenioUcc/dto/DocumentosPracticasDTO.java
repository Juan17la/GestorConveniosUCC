package com.gucc.GestorConvenioUcc.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentosPracticasDTO extends DocumentoConvenioDTO {
    private Boolean formatoSolicitud;
    private Boolean formatoTratamientoDatos;
    private Boolean formatoVinculacion;
    private Boolean copiaCedula;
    private Boolean copiaRUT;
    private Boolean copiaCamaraComercio;
}
