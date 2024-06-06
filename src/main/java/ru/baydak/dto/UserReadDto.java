package ru.baydak.dto;

import ru.baydak.entity.PersonalInfo;
import ru.baydak.entity.Role;

public record UserReadDto(Long id,
                          PersonalInfo personalInfo,
                          String username,
                          String info,
                          Role role,
                          CompanyReadDto companyReadDto) {


}
