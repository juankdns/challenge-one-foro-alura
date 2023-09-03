package com.foro.domain.service;

import com.foro.domain.entity.RespuestaEntity;
import com.foro.persistence.dto.RespuestaDto;
import com.foro.persistence.mapper.RespuestaMapper;
import com.foro.persistence.repository.RespuestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final RespuestaMapper respuestaMapper;

    public RespuestaDto save(RespuestaDto respuestaDto) {
        RespuestaEntity respuesta = respuestaMapper.toRespuesta(respuestaDto);
        return respuestaMapper.toRespuestaDto(respuestaRepository.save(respuesta));
    }

    public Optional<RespuestaDto> findById(Long id) {
        return respuestaRepository.findById(id).map(respuestaMapper::toRespuestaDto);
    }

    public List<RespuestaDto> findAll() {
        return respuestaMapper.toRespuestaList(respuestaRepository.findAll());
    }

    public void deleteById(Long id) {
        respuestaRepository.deleteById(id);
    }
}
