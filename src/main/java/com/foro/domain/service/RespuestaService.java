package com.foro.domain.service;

import com.foro.domain.StatusTopico;
import com.foro.domain.entity.RespuestaEntity;
import com.foro.domain.entity.TopicoEntity;
import com.foro.exception.NoContentException;
import com.foro.exception.NotFoundException;
import com.foro.persistence.dto.RespuestaDto;
import com.foro.persistence.mapper.RespuestaMapper;
import com.foro.persistence.mapper.TopicoMapper;
import com.foro.persistence.repository.RespuestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final TopicoService topicoService;
    private final RespuestaMapper respuestaMapper;
    private final TopicoMapper topicoMapper;

    @Transactional
    public RespuestaDto save(RespuestaDto respuestaDto) {
        RespuestaEntity respuesta = respuestaMapper.toRespuesta(respuestaDto);
        TopicoEntity topico = new TopicoEntity();
        StatusTopico status = topicoService.findStatus(respuesta.getIdTopico());

        if (status.equals(StatusTopico.NO_RESPONDIDO)) {
            topico.setId(respuesta.getIdTopico());
            topico.setStatus(StatusTopico.NO_SOLUCIONADO);
            topicoService.update(topicoMapper.toTopicoDto(topico));
        }

        return respuestaMapper.toRespuestaDto(respuestaRepository.save(respuesta));
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

    @Transactional
    public RespuestaDto update(RespuestaDto respuestaDto) throws NotFoundException {
        return findById(respuestaDto.id()).map(respuestaOpt -> {

            TopicoEntity topico = respuestaOpt.topico();
            RespuestaEntity respuesta = respuestaMapper.toRespuesta(respuestaOpt);

            if (!topico.getStatus().equals(StatusTopico.CERRADO)) {
                Long idTopico = respuestaDto.idTopico();
                String mensaje = respuestaDto.mensaje();
                Boolean solucion = respuestaDto.solucion();

                respuesta.setIdTopico(idTopico != null && idTopico >= 0 ? idTopico : respuesta.getIdTopico());
                respuesta.setMensaje(mensaje != null && !mensaje.isBlank() ? mensaje : respuesta.getMensaje());
                respuesta.setSolucion(solucion != null ? solucion : respuesta.getSolucion());

                if (Boolean.TRUE.equals(solucion) && topico.getStatus().equals(StatusTopico.NO_SOLUCIONADO)) {
                    topico.setStatus(StatusTopico.SOLUCIONADO);
                    topicoService.update(topicoMapper.toTopicoDto(topico));
                }
            }

            return respuestaMapper.toRespuestaDto(respuestaRepository.save(respuesta));
        }).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        if (existsById(id)) {
            Long idTopico = respuestaRepository.findIdTopico(id);
            respuestaRepository.updaterStatus(idTopico);
            respuestaRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }
}
