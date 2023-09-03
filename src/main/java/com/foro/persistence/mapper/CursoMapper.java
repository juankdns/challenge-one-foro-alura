package com.foro.persistence.mapper;

import com.foro.domain.entity.CursoEntity;
import com.foro.persistence.dto.CursoDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "categoria", target = "categoria")
    CursoDto toCursoDto(CursoEntity curso);

    List<CursoDto> toCursoList(List<CursoEntity> cursos);

    @InheritInverseConfiguration
    CursoEntity toCurso(CursoDto cursoDto);


}
