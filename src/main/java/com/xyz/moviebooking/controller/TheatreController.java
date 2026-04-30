package com.xyz.moviebooking.controller;

import com.xyz.moviebooking.dto.AllocateSeatsRequest;
import com.xyz.moviebooking.dto.CreateShowRequest;
import com.xyz.moviebooking.dto.ShowDetailsDTO;
import com.xyz.moviebooking.dto.TheatreShowsDTO;
import com.xyz.moviebooking.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @GetMapping("/cities/{cityId}/movies/{movieId}/theatres")
    public ResponseEntity<List<TheatreShowsDTO>> getTheatresAndShows(
            @PathVariable Long cityId,
            @PathVariable Long movieId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        List<TheatreShowsDTO> response = theatreService.getTheatresAndShows(cityId, movieId, date);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/theatres/{theatreId}/shows")
    public ResponseEntity<ShowDetailsDTO> createShow(@PathVariable Long theatreId, @RequestBody CreateShowRequest request) {
        ShowDetailsDTO response = theatreService.createShow(theatreId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/theatres/{theatreId}/shows/{showId}")
    public ResponseEntity<ShowDetailsDTO> updateShow(@PathVariable Long theatreId, @PathVariable Long showId, @RequestBody CreateShowRequest request) {
        ShowDetailsDTO response = theatreService.updateShow(theatreId, showId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/theatres/{theatreId}/shows/{showId}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long theatreId, @PathVariable Long showId) {
        theatreService.deleteShow(theatreId, showId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/shows/{showId}/seats")
    public ResponseEntity<Void> allocateSeats(@PathVariable Long showId, @RequestBody AllocateSeatsRequest request) {
        theatreService.allocateSeats(showId, request);
        return ResponseEntity.ok().build();
    }
}
