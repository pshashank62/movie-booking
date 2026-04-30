package com.xyz.moviebooking.dto;

import lombok.Data;
import java.util.List;

@Data
public class BulkCancelRequest {
    private List<Long> bookingIds;
}
