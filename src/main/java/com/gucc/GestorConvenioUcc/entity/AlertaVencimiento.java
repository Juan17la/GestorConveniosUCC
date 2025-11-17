package com.gucc.GestorConvenioUcc.entity;

import com.gucc.GestorConvenioUcc.enums.EstadoAlerta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertas_vencimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertaVencimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaGenerada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "convenio_id", nullable = false)
    private Convenio convenio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enviada_a_id", nullable = false)
    private Usuario enviadaA;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAlerta estado;
}
