package com.foro.domain.service;

import com.foro.domain.StatusTopico;
import com.foro.domain.entity.TopicoEntity;
import com.foro.exception.NoContentException;
import com.foro.exception.NotFoundException;
import com.foro.persistence.dto.TopicoDto;
import com.foro.persistence.mapper.TopicoMapper;
import com.foro.persistence.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final TopicoMapper topicoMapper;

    public TopicoDto save(TopicoDto topicoDto) {
        return topicoMapper.toTopicoDto(topicoRepository.save(topicoMapper.toTopico(topicoDto)));
    }

    public Optional<TopicoDto> findById(Long id) {
        return topicoRepository.findById(id).map(topicoMapper::toTopicoDto);
    }

    public boolean existsById(Long id) {
        return topicoRepository.existsById(id);
    }

    public List<TopicoDto> findAll() throws NoContentException {
        List<TopicoEntity> topicos = topicoRepository.findAll();

        if (topicos.isEmpty()) {
            throw new NoContentException();
        }

        return topicoMapper.toTopicoList(topicos);
    }

    public TopicoDto update(TopicoDto topicoDto) throws NotFoundException {
        return findById(topicoDto.id()).map(topicoOpt -> {
            TopicoEntity topico = topicoMapper.toTopico(topicoOpt);

            Long idCurso = topicoDto.idCurso();
            String titulo = topicoDto.titulo();
            String mensaje = topicoDto.mensaje();
            LocalDateTime fechaCreacion = topicoDto.fechaCreacion();
            StatusTopico status = topicoDto.status();

            topico.setIdCurso(idCurso >= 0 ? idCurso : topico.getIdCurso());
            topico.setTitulo(titulo != null && !titulo.isBlank() ? titulo : topico.getTitulo());
            topico.setMensaje(mensaje != null && !mensaje.isBlank() ? mensaje : topico.getMensaje());
            topico.setFechaCreacion(fechaCreacion != null ? fechaCreacion : topico.getFechaCreacion());
            topico.setStatus(status != null ? status : topico.getStatus());

            return topicoMapper.toTopicoDto(topicoRepository.save(topico));
        }).orElseThrow(NotFoundException::new);
    }

    public void deleteById(Long id) throws NotFoundException {
        if (existsById(id)) {
            topicoRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }
}
