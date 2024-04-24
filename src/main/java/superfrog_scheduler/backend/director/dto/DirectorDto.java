package superfrog_scheduler.backend.director.dto;

import jakarta.validation.constraints.NotEmpty;

public record DirectorDto(String id,
    @NotEmpty(message = "Email is required.")
    String email,
    boolean active,
    @NotEmpty(message = "Roles are required.")
    String roles) {}
