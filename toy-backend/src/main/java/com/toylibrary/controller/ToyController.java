package com.toylibrary.controller;

import com.toylibrary.model.Toy;
import com.toylibrary.service.ToyService;
import com.toylibrary.dto.ToyDTO;
import com.toylibrary.dto.ToyMapper;

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
    public ResponseEntity<ToyDTO> createToy(@RequestBody Toy toy, @RequestParam Long ownerId) {
        Toy createdToy = toyService.createToy(toy, ownerId);
        return new ResponseEntity<>(ToyMapper.toDTO(createdToy), HttpStatus.CREATED);
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<ToyDTO> getToyById(@PathVariable Long id) {
        Toy toy = toyService.getToyById(id);
        return new ResponseEntity<>(ToyMapper.toDTO(toy), HttpStatus.OK);
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToy(@PathVariable Long id) {
        toyService.deleteToy(id);
        return ResponseEntity.noContent().build();
    }

 
    @GetMapping
    public ResponseEntity<List<ToyDTO>> getAllToys() {
        List<ToyDTO> dtos = toyService.getAllToys()
            .stream().map(ToyMapper::toDTO).toList();
        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/available")
    public ResponseEntity<List<ToyDTO>> getAvailableToys() {
        List<ToyDTO> dtos = toyService.getAvailableToys()
            .stream().map(ToyMapper::toDTO).toList();
        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ToyDTO>> getToysByOwner(@PathVariable Long ownerId) {
        List<ToyDTO> dtos = toyService.getToysByOwner(ownerId)
            .stream().map(ToyMapper::toDTO).toList();
        return ResponseEntity.ok(dtos);
    }


    @PutMapping("/{id}/{ownerId}")
    public ResponseEntity<ToyDTO> updateToy(@PathVariable Long id, @PathVariable Long ownerId,
                                            @RequestBody Toy updatedToy) {
        Toy updated = toyService.updateToy(id, updatedToy, ownerId);
        return new ResponseEntity<>(ToyMapper.toDTO(updated), HttpStatus.OK);
    }
}
