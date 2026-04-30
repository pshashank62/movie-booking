package com.xyz.moviebooking.repository;

import com.xyz.moviebooking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByTheatreCityIdAndMovieIdAndShowDate(Long cityId, Long movieId, LocalDate showDate);
}
