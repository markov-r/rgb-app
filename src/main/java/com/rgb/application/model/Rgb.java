package com.rgb.application.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString @EqualsAndHashCode
public class Rgb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int red;
    private int green;
    private int blue;
}
