package com.xyz.moviebooking.service;

import com.xyz.moviebooking.dto.AllocateSeatsRequest;
import com.xyz.moviebooking.dto.CreateShowRequest;
import com.xyz.moviebooking.dto.ShowDetailsDTO;
import com.xyz.moviebooking.dto.TheatreShowsDTO;
import com.xyz.moviebooking.exception.ResourceNotFoundException;
import com.xyz.moviebooking.model.*;
import com.xyz.moviebooking.repository.MovieRepository;
import com.xyz.moviebooking.repository.ShowRepository;
import com.xyz.moviebooking.repository.ShowSeatRepository;
import com.xyz.moviebooking.repository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheatreService {

    private final ShowRepository showRepository;
    private final TheatreRepository theatreRepository;
    private final MovieRepository movieRepository;
    private final ShowSeatRepository showSeatRepository;

    public List<TheatreShowsDTO> getTheatresAndShows(Long cityId, Long movieId, LocalDate date) {
        List<Show> shows = showRepository.findByTheatreCityIdAndMovieIdAndShowDate(cityId, movieId, date);

        Map<Long, List<Show>> showsByTheatre = shows.stream()
                .collect(Collectors.groupingBy(show -> show.getTheatre().getId()));

        return showsByTheatre.entrySet().stream()
                .map(entry -> {
                    Show firstShow = entry.getValue().get(0);
                    List<ShowDetailsDTO> showDetails = entry.getValue().stream()
                            .map(s -> new ShowDetailsDTO(s.getId(), s.getStartTime(), s.getEndTime()))
                            .collect(Collectors.toList());

                    return TheatreShowsDTO.builder()
                            .theatreId(firstShow.getTheatre().getId())
                            .theatreName(firstShow.getTheatre().getName())
                            .address(firstShow.getTheatre().getAddress())
                            .shows(showDetails)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public ShowDetailsDTO createShow(Long theatreId, CreateShowRequest request) {
        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new ResourceNotFoundException("Theatre not found"));
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        Show show = Show.builder()
                .theatre(theatre)
                .movie(movie)
                .showDate(request.getShowDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();

        show = showRepository.save(show);
        return new ShowDetailsDTO(show.getId(), show.getStartTime(), show.getEndTime());
    }

    @Transactional
    public ShowDetailsDTO updateShow(Long theatreId, Long showId, CreateShowRequest request) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found"));

        if (!show.getTheatre().getId().equals(theatreId)) {
            throw new IllegalArgumentException("Show does not belong to this theatre");
        }

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        show.setMovie(movie);
        show.setShowDate(request.getShowDate());
        show.setStartTime(request.getStartTime());
        show.setEndTime(request.getEndTime());

        show = showRepository.save(show);
        return new ShowDetailsDTO(show.getId(), show.getStartTime(), show.getEndTime());
    }

    @Transactional
    public void deleteShow(Long theatreId, Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found"));

        if (!show.getTheatre().getId().equals(theatreId)) {
            throw new IllegalArgumentException("Show does not belong to this theatre");
        }

        // Delete associated seats first
        List<ShowSeat> seats = showSeatRepository.findByShowId(showId);
        showSeatRepository.deleteAll(seats);
        showRepository.delete(show);
    }

    @Transactional
    public void allocateSeats(Long showId, AllocateSeatsRequest request) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found"));

        for (int i = 0; i < request.getNumberOfSeats(); i++) {
            ShowSeat showSeat = ShowSeat.builder()
                    .show(show)
                    .status(SeatStatus.AVAILABLE)
                    .price(request.getPrice())
                    .build();
            showSeatRepository.save(showSeat);
        }
    }
}
