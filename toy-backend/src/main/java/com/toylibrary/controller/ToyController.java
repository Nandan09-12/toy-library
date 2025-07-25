package com.toylibrary.controller;

import com.toylibrary.model.Toy;
import com.toylibrary.service.ToyService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/toys")
@RequiredArgsConstructor
public class ToyController {

    private final ToyService toyService;

    @PostMapping("/create")
    public ResponseEntity<Toy> createToy(@RequestBody Toy toy, @RequestParam Long ownerId) {
        Toy createdToy = toyService.createToy(toy, ownerId);
        return new ResponseEntity<>(createdToy, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Toy> getToyById(@PathVariable Long id) {
        Toy toy = toyService.getToyById(id);
        return new ResponseEntity<>(toy, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToy(@PathVariable Long id) {
        toyService.deleteToy(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Toy>> getAllToys() {
        return ResponseEntity.ok(toyService.getAllToys());
    }

    @GetMapping("/available")
    public ResponseEntity<List<Toy>> getAvailableToys() {
        return ResponseEntity.ok(toyService.getAvailableToys());
    }

    @GetMapping("/owner/{ownerId}")
        public ResponseEntity<List<Toy>> getToysByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(toyService.getToysByOwner(ownerId));
    }


}

