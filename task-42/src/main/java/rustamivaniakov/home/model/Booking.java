package rustamivaniakov.home.model;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Booking {
    private String id;
    @NonNull()
    private String deviceId;
    @NonNull()
    private String userId;
    @NonNull()
    private Instant startTime;
    @NonNull()
    private Instant endTime;

    private boolean isDeleted;

    public Booking() {
        this.id = UUID.randomUUID().toString();
    }
}