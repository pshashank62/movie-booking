package com.xyz.moviebooking.controller;

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
}
