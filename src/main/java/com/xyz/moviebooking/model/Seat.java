package com.xyz.moviebooking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
