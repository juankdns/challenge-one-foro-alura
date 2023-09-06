package com.foro.api.controller;

import com.foro.domain.entity.TopicoEntity;
import com.foro.domain.service.TopicoService;
import com.foro.exception.NoContentException;
import com.foro.exception.NotFoundException;
import com.foro.persistence.dto.CursoDto;
import com.foro.persistence.dto.TopicoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topico")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoDto> save(@RequestBody TopicoDto topicoDto) {
        TopicoDto topico = topicoService.save(topicoDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(topico.id())
                .toUri();

        return ResponseEntity.created(location).body(topico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(topicoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TopicoDto>> findAll() {
        try {
            return ResponseEntity.ok(topicoService.findAll());
        } catch (NoContentException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<TopicoDto> update(@RequestBody TopicoDto topicoDto) {
        try {
            return ResponseEntity.ok(topicoService.update(topicoDto));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            topicoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
