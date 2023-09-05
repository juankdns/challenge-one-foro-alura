package com.foro.domain.service;

import com.foro.domain.entity.CursoEntity;
import com.foro.exception.NotFoundException;
import com.foro.persistence.dto.CursoDto;
import com.foro.persistence.mapper.CursoMapper;
import com.foro.persistence.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;


    public CursoDto save(CursoDto cursoDto) {
        return cursoMapper.toCursoDto(cursoRepository.save(cursoMapper.toCurso(cursoDto)));
    }

    public Optional<CursoDto> findById(Long id) {
        return cursoRepository.findById(id).map(cursoMapper::toCursoDto);
    }

    public boolean existsById(Long id) {
        return cursoRepository.existsById(id);
    }

    public List<CursoDto> findAll() {
        return cursoMapper.toCursoList(cursoRepository.findAll());
    }

    public CursoDto update(CursoDto cursoDto) throws NotFoundException {
        return findById(cursoDto.id()).map(cursoOpt -> {
            CursoEntity curso = cursoMapper.toCurso(cursoOpt);

            String nombre = cursoDto.nombre();
            String categoria = cursoDto.categoria();

            curso.setNombre(nombre != null && !nombre.isBlank() ? nombre : curso.getNombre());
            curso.setCategoria(categoria != null && !categoria.isBlank() ? categoria : curso.getCategoria());

            return cursoMapper.toCursoDto(cursoRepository.save(curso));
        }).orElseThrow(NotFoundException::new);
    }

    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }
}
