package com.xyz.moviebooking.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;

import java.util.List;

@Data
public class BookTicketRequest {
    @NotNull
    private Long showId;

    @NotEmpty
    private List<Long> showSeatIds;

    @Email
    @NotNull
    private String userEmail;
}
