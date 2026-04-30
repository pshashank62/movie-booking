package com.xyz.moviebooking.repository;

import com.xyz.moviebooking.model.ShowSeat;
import com.xyz.moviebooking.model.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findByIdInAndStatus(List<Long> ids, SeatStatus status);
}
