package com.xyz.moviebooking.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateShowRequest {
    private Long movieId;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
