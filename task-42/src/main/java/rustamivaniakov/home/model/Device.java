package rustamivaniakov.home.model;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Device {
    private String id;
    @NonNull
    private String name;
    @NonNull
    private Technology technology;
    private String fonoID;


    private boolean isDeleted;


    public enum Technology {
        T2G,
        T3G,
        T4G
    }

}