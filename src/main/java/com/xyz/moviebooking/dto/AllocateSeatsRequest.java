package com.xyz.moviebooking.dto;

import lombok.Data;

@Data
public class AllocateSeatsRequest {
    private int numberOfSeats;
    private Double price;
}
