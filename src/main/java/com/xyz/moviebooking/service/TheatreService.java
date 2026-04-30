package com.xyz.moviebooking.service;

import com.xyz.moviebooking.dto.ShowDetailsDTO;
import com.xyz.moviebooking.dto.TheatreShowsDTO;
import com.xyz.moviebooking.model.Show;
import com.xyz.moviebooking.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheatreService {

    private final ShowRepository showRepository;

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
}
