package com.xyz.moviebooking.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowDetailsDTO {
    private Long showId;
    private LocalTime startTime;
    private LocalTime endTime;
}
