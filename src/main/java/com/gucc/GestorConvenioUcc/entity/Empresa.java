package com.gucc.GestorConvenioUcc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empresas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String nit;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String representante;

    // ✅ NUEVA: Relación inversa con Convenios
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Convenio> convenios = new ArrayList<>();

    // ✅ NUEVA: Relación inversa con ReportesEmpresa
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReporteEmpresa> reportes = new ArrayList<>();
}