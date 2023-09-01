package com.foro.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuesta")
@Getter
@Setter
@NoArgsConstructor
public class RespuestaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "id_topico", nullable = false)
    private Long idTopico;

    @Column(nullable = false)
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean solucion = false;

    @ManyToOne
    @JoinColumn(name = "id_topico", referencedColumnName = "id", insertable = false, updatable = false)
    private TopicoEntity topico;

}