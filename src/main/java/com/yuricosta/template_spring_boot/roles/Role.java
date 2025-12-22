package com.yuricosta.template_spring_boot.roles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Table(name = "roles")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Role implements GrantedAuthority {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String authority;
}
