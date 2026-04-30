package com.xyz.moviebooking.dto;

import com.xyz.moviebooking.model.BookingStatus;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class BookTicketResponse {
    private Long bookingId;
    private String userEmail;
    private Double totalAmount;
    private BookingStatus status;
}
