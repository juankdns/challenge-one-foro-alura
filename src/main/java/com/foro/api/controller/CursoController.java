package com.foro.api.controller;

import com.foro.domain.service.CursoService;
import com.foro.exception.NoContentException;
import com.foro.exception.NotFoundException;
import com.foro.persistence.dto.CursoDto;
import com.foro.validator.SaveValidator;
import com.foro.validator.UpdateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoDto> save(@Validated(SaveValidator.class) @RequestBody CursoDto cursoDto) {
        CursoDto curso = cursoService.save(cursoDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(curso.id())
                .toUri();

        return ResponseEntity.created(location).body(curso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(cursoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CursoDto>> findAll() {
        try {
            return ResponseEntity.ok(cursoService.findAll());
        } catch (NoContentException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<CursoDto> update(@Validated(UpdateValidator.class) @RequestBody CursoDto cursoDto) {
        try {
            return ResponseEntity.ok(cursoService.update(cursoDto));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            cursoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
