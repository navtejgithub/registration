package com.registration.registration.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private long id;
    @NonNull
    private String name;
    @Email
    private String email;
    @Size(min =10,max = 11)
    private String mobile;
}
