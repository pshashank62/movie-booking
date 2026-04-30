package com.xyz.moviebooking.controller;

import com.xyz.moviebooking.dto.BookTicketRequest;
import com.xyz.moviebooking.dto.BookTicketResponse;
import com.xyz.moviebooking.dto.BulkCancelRequest;
import com.xyz.moviebooking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookTicketResponse> bookTickets(@Valid @RequestBody BookTicketRequest request) {
        BookTicketResponse response = bookingService.bookTickets(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/movies-theatres/{cityId}")
    public ResponseEntity<Map<String, String>> getAllTheMoviesTheatresInCity(@PathVariable Long cityId) {
        Map<String, String> availability = bookingService.getAllTheMoviesTheatresInCity(cityId);
        return ResponseEntity.ok(availability);
    }

    @PostMapping("/bulk-cancel")
    public ResponseEntity<Void> bulkCancelBookings(@RequestBody BulkCancelRequest request) {
        bookingService.bulkCancelBookings(request.getBookingIds());
        return ResponseEntity.ok().build();
    }
}
