package com.foro.persistence.mapper;

import com.foro.domain.entity.RespuestaEntity;
import com.foro.persistence.dto.RespuestaDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TopicoMapper.class})
public interface RespuestaMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "idTopico", target = "idTopico")
    @Mapping(source = "mensaje", target = "mensaje")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "solucion", target = "solucion", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(source = "topico", target = "topico")
    RespuestaDto toRespuestaDto(RespuestaEntity respuesta);

    List<RespuestaDto> toRespuestaList(List<RespuestaEntity> repuestas);

    @InheritInverseConfiguration
    RespuestaEntity toRespuesta(RespuestaDto respuestaDto);

}
