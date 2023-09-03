package com.foro.domain.service;

import com.foro.domain.entity.TopicoEntity;
import com.foro.persistence.dto.TopicoDto;
import com.foro.persistence.mapper.TopicoMapper;
import com.foro.persistence.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final TopicoMapper topicoMapper;

    public TopicoDto save(TopicoDto topicoDto) {
        TopicoEntity topico = topicoMapper.toTopico(topicoDto);
        return topicoMapper.toTopicoDto(topicoRepository.save(topico));
    }

    public Optional<TopicoDto> findById(Long id) {
        return topicoRepository.findById(id).map(topicoMapper::toTopicoDto);
    }

    public List<TopicoDto> findAll() {
        return topicoMapper.toTopicoList(topicoRepository.findAll());
    }

    public void deleteById(Long id) {
        topicoRepository.deleteById(id);
    }
}
