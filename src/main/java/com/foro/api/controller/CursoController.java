package com.foro.api.controller;

import com.foro.domain.service.CursoService;
import com.foro.exception.NotFoundException;
import com.foro.persistence.dto.CursoDto;
import com.foro.persistence.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoDto> save(@RequestBody CursoDto cursoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> findById(@PathVariable Long id) {
        return ResponseEntity.of(cursoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CursoDto>> findAll() {
        List<CursoDto> cursos = cursoService.findAll();
        return ResponseEntity.status(cursos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(cursos);
    }

    @PutMapping
    public ResponseEntity<CursoDto> update(@RequestBody CursoDto cursoDto) {
        try {
            return ResponseEntity.ok(cursoService.update(cursoDto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean exits = cursoService.existsById(id);
        if (exits) {
            cursoService.deleteById(id);
        }
        return ResponseEntity.status(exits ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).build();
    }
}
