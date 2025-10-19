package com.murilovc.workly.repository;

import com.murilovc.workly.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneById(@NonNull @NotNull final Long id);

    Optional<User> findOneByUsernameIgnoreCase(@NonNull @NotNull final String username);

    boolean existsByUsernameIgnoreCase(@NonNull @NotNull final String username);
}
