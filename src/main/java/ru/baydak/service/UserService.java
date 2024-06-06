package ru.baydak.service;

import jakarta.transaction.Transactional;
import jakarta.validation.*;
import ru.baydak.dao.UserRepository;
import ru.baydak.dto.UserCreateDto;
import ru.baydak.dto.UserReadDto;
import ru.baydak.entity.User;
import ru.baydak.mapper.Mapper;
import ru.baydak.mapper.UserCreateMapper;
import ru.baydak.mapper.UserReadMapper;
import ru.baydak.validation.UpdateCheck;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;

    @Transactional
    public Long create(UserCreateDto userDto) {
        // validation
        @Cleanup ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<UserCreateDto>> validateResult = validator.validate(userDto, UpdateCheck.class);
        if (!validateResult.isEmpty()) {
            throw new ConstraintViolationException(validateResult);
        }

        var userEntity = userCreateMapper.mapFrom(userDto);
        return userRepository.save(userEntity).getId();
    }

    public boolean delete(Long id) {
        Optional<User> maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(user.getId()));
        return maybeUser.isPresent();
    }

    @Transactional
    public Optional<UserReadDto> findById(Long id) {
        return findById(id, userReadMapper);
    }

    @Transactional
    public <T> Optional<T> findById(Long id, Mapper<User, T> mapper) {
//        Map<String, Object> properties = Map.of(
//                GraphSemantic.LOAD.getJpaHintName(), userRepository.getEntityManager().getEntityGraph("WithCompany")
//        );
        //todo add "properties"
        return userRepository.findById(id)
                .map(mapper::mapFrom);
    }
}
