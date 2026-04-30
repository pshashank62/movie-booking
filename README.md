# Movie Ticket Booking Platform

A highly scalable, RESTful B2B and B2C movie ticket booking platform built with Java 17 and Spring Boot. This application allows theatre partners to onboard and manage their inventory while enabling end customers to seamlessly browse shows and book tickets with dynamic pricing and custom discount rules.

## Features

*   **B2C Browse (Read Scenario):** Browse theatres and shows for a specific city, movie, and date.
*   **B2C Booking (Write Scenario):** Select specific seats for a show and lock/book them concurrently.
*   **Dynamic Discount Engine:** Automatically applies business rules at checkout:
    *   50% discount on the third ticket.
    *   20% discount on afternoon shows (between 12:00 PM and 4:00 PM).
*   **Concurrency Control:** Implements `@Transactional(isolation = Isolation.SERIALIZABLE)` to prevent double-booking anomalies on the database level.
*   **Clean Architecture:** Organized strictly into Controller, Service, and Repository layers using DTO patterns to protect internal data models.
*   **Global Exception Handling:** Clean, user-friendly JSON error responses for edge cases like "Seat Not Available" or "Resource Not Found".

## Tech Stack

*   **Java 17**
*   **Spring Boot 3.x**
*   **Spring Data JPA**
*   **H2 Database** (In-memory, for demonstration and easy setup)
*   **Maven**
*   **Lombok**

## Getting Started

### Prerequisites
*   [Java 17](https://adoptium.net/) or higher installed.
*   Maven (Optional, the wrapper is included).

### How to Run Locally

1. **Clone the repository** (if applicable) and navigate to the root folder:
   ```bash
   cd movie-booking
   ```

2. **Run the application** using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
   *(On Windows, use `mvnw.cmd spring-boot:run`)*

3. The application will start on port `8080`.
   *   H2 Database Console is available at: `http://localhost:8080/h2-console`
   *   JDBC URL: `jdbc:h2:mem:moviedb`
   *   Username: `sa` / Password: *(leave blank)*

## API Endpoints

### 1. Browse Theatres and Shows (GET)
**Endpoint:** `/api/v1/cities/{cityId}/movies/{movieId}/theatres?date={YYYY-MM-DD}`
*Fetches all theatres and showtimes for a specific movie in a given city.*

### 2. Admin/Utility - Get All Movies and Theatres in City (GET)
**Endpoint:** `/api/v1/movies-theatres/{cityId}`
*Provides a simple mapping of available movies and the theatres playing them in a specific city.*

### 3. Book Tickets (POST)
**Endpoint:** `/api/v1/bookings`
*Books the requested seats and returns the confirmation with the total amount after applying discounts.*
**Payload Example:**
```json
{
  "showId": 1,
  "showSeatIds": [1, 2, 3],
  "userEmail": "customer@example.com"
}
```

### 4. Bulk Cancel Bookings (POST)
**Endpoint:** `/api/v1/bulk-cancel`
*Cancels multiple bookings at once and releases the associated show inventory back to 'AVAILABLE'.*
**Payload Example:**
```json
{
  "bookingIds": [101, 102]
}
```

### 5. Create Show (POST)
**Endpoint:** `/api/v1/theatres/{theatreId}/shows`
*Allows a theatre to schedule a new show for a specific movie.*
**Payload Example:**
```json
{
  "movieId": 1,
  "showDate": "2026-05-01",
  "startTime": "10:00:00",
  "endTime": "12:30:00"
}
```

### 6. Update Show (PUT)
**Endpoint:** `/api/v1/theatres/{theatreId}/shows/{showId}`
*Allows a theatre to update the movie or timings of an existing show.*

### 7. Delete Show (DELETE)
**Endpoint:** `/api/v1/theatres/{theatreId}/shows/{showId}`
*Allows a theatre to completely delete a show. This cascade-deletes any seat inventory allocated to it.*

### 8. Allocate Seat Inventory (POST)
**Endpoint:** `/api/v1/shows/{showId}/seats`
*Bulk allocates a specific number of generic seats to a show with a default price.*
**Payload Example:**
```json
{
  "numberOfSeats": 100,
  "price": 350.0
}
```

## License

This project is licensed under the [MIT License](LICENSE).
