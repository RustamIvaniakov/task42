package rustamivaniakov.home.model;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    private String id;
    @NonNull
    private String name;

    private boolean isDeleted;
}