package com.gucc.GestorConvenioUcc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("PRACTICAS")
public class DocumentosPracticas extends DocumentoConvenio {
    private Boolean formatoSolicitud;
    private Boolean formatoTratamientoDatos;
    private Boolean formatoVinculacion;
    private Boolean copiaCedula;
    private Boolean copiaRUT;
    private Boolean copiaCamaraComercio;
}
