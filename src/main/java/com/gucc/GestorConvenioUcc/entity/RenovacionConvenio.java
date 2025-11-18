package com.gucc.GestorConvenioUcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "renovaciones_convenio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RenovacionConvenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "convenio_original_id", nullable = false)
    private Convenio convenioOriginal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nueva_peticion_id")
    private Peticion nuevaPeticion;

    @Column(nullable = false)
    @JoinColumn(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
