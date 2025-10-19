package com.murilovc.workly.domain.entity;

import com.murilovc.workly.domain.enumeration.RoleKeyEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@Table(name = "app_user")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractBaseEntity {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 150)
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "role", nullable = false)
    private RoleKeyEnum role;

    @Setter
    @Column(name = "email")
    private String email;

    @Setter
    @Size(min = 10, max = 14)
    @Column(name = "phone", length = 14)
    private String phone;

    @Setter
    @Size(min = 10, max = 600)
    @Column(name = "experience", length = 600)
    private String experience;

    @Setter
    @Size(min = 10, max = 600)
    @Column(name = "education", length = 600)
    private String education;

    public User(
                @NonNull @NotNull final String username,
                @NonNull @NotNull final String name,
                @NonNull @NotNull final String password,
                @NonNull @NotNull final RoleKeyEnum role,
                final String email,
                final String phone,
                final String experience,
                final String education) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.experience = experience;
        this.education = education;
    }

    public User(final Long id) {
        super(id);
    }
}
