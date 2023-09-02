package com.foro.persistence.mapper;

import com.foro.domain.entity.RespuestaEntity;
import com.foro.persistence.dto.RespuestaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TopicoMapper.class})
public interface RespuestaMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "idTopico", target = "idTopico")
    @Mapping(source = "mensaje", target = "mensaje")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    @Mapping(source = "solucion", target = "solucion")
    @Mapping(source = "topico", target = "topico")
    RespuestaDto respuestaToRespuestaDto(RespuestaEntity respuesta);

    List<RespuestaEntity> respuestaToRespuestaList(List<RespuestaEntity> repuestas);

}
