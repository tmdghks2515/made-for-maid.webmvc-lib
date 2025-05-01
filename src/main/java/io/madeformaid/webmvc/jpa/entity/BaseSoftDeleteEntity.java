package io.madeformaid.webmvc.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseSoftDeleteEntity extends BaseEntity {
    @Column(name="deleted_at")
    private LocalDateTime deletedAt;
}
