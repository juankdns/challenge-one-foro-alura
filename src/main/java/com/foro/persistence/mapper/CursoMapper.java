package com.foro.persistence.mapper;

import com.foro.domain.entity.CursoEntity;
import com.foro.persistence.dto.CursoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "categoria", target = "categoria")
    CursoDto cursoToCursoDto(CursoEntity curso);

}
