package com.xyz.moviebooking.controller;

import com.xyz.moviebooking.dto.BookTicketRequest;
import com.xyz.moviebooking.dto.BookTicketResponse;
import com.xyz.moviebooking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
