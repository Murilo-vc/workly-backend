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

@Table(name = "role")
@Entity(name = "Role")
@Getter
@Setter
@NoArgsConstructor
public class Role extends AbstractBaseEntity {

    @NonNull
    @NotNull
    @NotBlank
    @Size(max = 30)
    @Enumerated(EnumType.STRING)
    @Column(name = "role_key", length = 30, unique = true, nullable = false)
    private RoleKeyEnum key;

    @NonNull
    @NotNull
    @NotBlank
    @Column(name = "role_name", length = 50, nullable = false)
    private String name;

    public Role(final Long id) {
        super(id);
    }
}
