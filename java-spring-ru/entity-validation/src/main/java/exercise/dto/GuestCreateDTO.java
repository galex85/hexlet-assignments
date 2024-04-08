package exercise.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {
    @NotBlank
    private String name;

    @Column(unique = true)
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(\\+\\d{11,13})", message = "Phone must start with +", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Size(min=11, max=13)
    private String phoneNumber;

    @Size(min=4, max=4)
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;
}
// END
