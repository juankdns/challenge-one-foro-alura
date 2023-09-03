package com.foro.persistence.mapper;

import com.foro.domain.entity.TopicoEntity;
import com.foro.persistence.dto.TopicoDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CursoMapper.class, RespuestaMapper.class})
public interface TopicoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "idCurso", target = "idCurso")
    @Mapping(source = "titulo", target = "titulo")
    @Mapping(source = "mensaje", target = "mensaje")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "curso", target = "curso")
    @Mapping(source = "respuestas", target = "respuestas")
    TopicoDto toTopicoDto(TopicoEntity topico);

    List<TopicoDto> toTopicoList(List<TopicoEntity> topicos);

    @InheritInverseConfiguration
    TopicoEntity toTopico(TopicoDto topicoDto);

}
