package com.xyz.moviebooking.service;

import com.xyz.moviebooking.dto.BookTicketRequest;
import com.xyz.moviebooking.dto.BookTicketResponse;
import com.xyz.moviebooking.exception.ResourceNotFoundException;
import com.xyz.moviebooking.exception.SeatNotAvailableException;
import com.xyz.moviebooking.model.Booking;
import com.xyz.moviebooking.model.BookingStatus;
import com.xyz.moviebooking.model.SeatStatus;
import com.xyz.moviebooking.model.Show;
import com.xyz.moviebooking.model.ShowSeat;
import com.xyz.moviebooking.repository.BookingRepository;
import com.xyz.moviebooking.repository.ShowRepository;
import com.xyz.moviebooking.repository.ShowSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BookTicketResponse bookTickets(BookTicketRequest request) {
        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new ResourceNotFoundException("Show not found"));

        List<ShowSeat> showSeats = showSeatRepository.findByIdInAndStatus(request.getShowSeatIds(),
                SeatStatus.AVAILABLE);

        if (showSeats.size() != request.getShowSeatIds().size()) {
            throw new SeatNotAvailableException("One or more selected seats are not available");
        }

        // Apply Offers
        // 50% discount on the third ticket
        // 20% discount for the afternoon show (e.g., 12 PM to 4 PM)
        double totalAmount = 0;
        for (int i = 0; i < showSeats.size(); i++) {
            ShowSeat seat = showSeats.get(i);
            double seatPrice = seat.getPrice();

            // 50% discount on the third ticket
            if ((i + 1) % 3 == 0) {
                seatPrice = seatPrice * 0.5;
            }

            totalAmount += seatPrice;
        }

        // 20% discount for afternoon show (12:00 to 16:00)
        int startHour = show.getStartTime().getHour();
        if (startHour >= 12 && startHour <= 16) {
            totalAmount = totalAmount * 0.8;
        }

        Booking booking = Booking.builder()
                .show(show)
                .userEmail(request.getUserEmail())
                .bookingTime(LocalDateTime.now())
                .totalAmount(totalAmount)
                .status(BookingStatus.CONFIRMED)
                .build();

        booking = bookingRepository.save(booking);

        for (ShowSeat seat : showSeats) {
            seat.setStatus(SeatStatus.BOOKED);
            seat.setBooking(booking);
        }
        showSeatRepository.saveAll(showSeats);

        return BookTicketResponse.builder()
                .bookingId(booking.getId())
                .userEmail(booking.getUserEmail())
                .totalAmount(totalAmount)
                .status(booking.getStatus())
                .build();
    }

    public Map<String, String> getAllTheMoviesTheatresInCity(Long cityId) {
        Map<String, String> moviesTheatresMap = new HashMap<>();
        List<Show> shows = showRepository.findByTheatreCityId(cityId);

        for (Show show : shows) {
            moviesTheatresMap.put(show.getMovie().getTitle(), show.getTheatre().getName());
        }

        return moviesTheatresMap;
    }
}
