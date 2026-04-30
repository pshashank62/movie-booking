package com.xyz.moviebooking.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheatreShowsDTO {
    private Long theatreId;
    private String theatreName;
    private String address;
    private List<ShowDetailsDTO> shows;
}
