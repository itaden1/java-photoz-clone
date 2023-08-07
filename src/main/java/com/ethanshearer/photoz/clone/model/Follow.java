package com.ethanshearer.photoz.clone.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("FOLLOW")
public class Follow {
    @Id private Integer id;
    @NotEmpty private boolean accepted;
    private LocalDateTime created;
    private LocalDateTime acceptedDatTime;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private User follower;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private User followee;

}
