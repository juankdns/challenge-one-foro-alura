package com.foro.api.controller;


import com.foro.domain.service.RespuestaService;
import com.foro.exception.NoContentException;
import com.foro.exception.NotFoundException;
import com.foro.persistence.dto.RespuestaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/respuesta")
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<RespuestaDto> save(@RequestBody RespuestaDto respuestaDto) {
        RespuestaDto respuesta = respuestaService.save(respuestaDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id)")
                .buildAndExpand(respuesta.id())
                .toUri();

        return ResponseEntity.created(location).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(respuestaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RespuestaDto>> findAll() {
        try {
            return ResponseEntity.ok(respuestaService.findAll());
        } catch (NoContentException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<RespuestaDto> update(@RequestBody RespuestaDto respuestaDto) {
        try {
            return ResponseEntity.ok(respuestaService.update(respuestaDto));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            respuestaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
