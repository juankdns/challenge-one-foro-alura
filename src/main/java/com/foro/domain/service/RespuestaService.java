package com.foro.domain.service;

import com.foro.domain.entity.RespuestaEntity;
import com.foro.exception.NoContentException;
import com.foro.exception.NotFoundException;
import com.foro.persistence.dto.RespuestaDto;
import com.foro.persistence.mapper.RespuestaMapper;
import com.foro.persistence.repository.RespuestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final RespuestaMapper respuestaMapper;

    public RespuestaDto save(RespuestaDto respuestaDto) {
        return respuestaMapper.toRespuestaDto(respuestaRepository.save(respuestaMapper.toRespuesta(respuestaDto)));
    }

    public Optional<RespuestaDto> findById(Long id) {
        return respuestaRepository.findById(id).map(respuestaMapper::toRespuestaDto);
    }

    public boolean existsById(Long id) {
        return respuestaRepository.existsById(id);
    }

    public List<RespuestaDto> findAll() throws NoContentException {
        List<RespuestaEntity> respuestas = respuestaRepository.findAll();

        if (respuestas.isEmpty()) {
            throw new NoContentException();
        }

        return respuestaMapper.toRespuestaList(respuestas);
    }

    public RespuestaDto update(RespuestaDto respuestaDto) throws NotFoundException {
        return findById(respuestaDto.id()).map(respuestaOpt -> {

            RespuestaEntity respuesta = respuestaMapper.toRespuesta(respuestaOpt);

            Long idTopico = respuestaDto.idTopico();
            String mensaje = respuestaDto.mensaje();
            LocalDateTime fechaCreacion = respuestaDto.fechaCreacion();
            Boolean solucion = respuestaDto.solucion();

            respuesta.setIdTopico(idTopico >= 0 ? idTopico : respuesta.getIdTopico());
            respuesta.setMensaje(mensaje != null && !mensaje.isBlank() ? mensaje : respuesta.getMensaje());
            respuesta.setFechaCreacion(fechaCreacion != null ? fechaCreacion : respuesta.getFechaCreacion());
            respuesta.setSolucion(solucion != null ? solucion : respuesta.getSolucion());

            return respuestaMapper.toRespuestaDto(respuestaRepository.save(respuesta));
        }).orElseThrow(NotFoundException::new);
    }

    public void deleteById(Long id) throws NotFoundException {
        if (existsById(id)) {
            respuestaRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }
}
