package ru.baydak.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import ru.baydak.entity.PersonalInfo;
import ru.baydak.entity.Role;
import ru.baydak.validation.UpdateCheck;

public record UserCreateDto(@Valid PersonalInfo personalInfo,
                            @NotNull String username,
                            String info,
                            @NotNull(groups = UpdateCheck.class) Role role,
                            Integer companyId) {
}
