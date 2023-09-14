package com.foro.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.foro.domain.StatusTopico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topico")
@Getter
@Setter
@NoArgsConstructor
public class TopicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private CursoEntity curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonManagedReference
    private List<RespuestaEntity> respuestas;
}
