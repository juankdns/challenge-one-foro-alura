package com.foro.domain.service;

import com.foro.domain.entity.CursoEntity;
import com.foro.persistence.dto.CursoDto;
import com.foro.persistence.mapper.CursoMapper;
import com.foro.persistence.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;


    public CursoDto save(CursoDto cursoDto) {
        CursoEntity curso = cursoMapper.toCurso(cursoDto);
        return cursoMapper.toCursoDto(cursoRepository.save(curso));
    }

    public Optional<CursoDto> findById(Long id) {
        return cursoRepository.findById(id).map(cursoMapper::toCursoDto);
    }

    public List<CursoDto> findAll() {
        return cursoMapper.toCursoList(cursoRepository.findAll());
    }

    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }
}
