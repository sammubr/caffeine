package br.com.sammubr.caffeine.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(value = "car")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {

    @Id
    private String id;

    @Indexed(unique=true)
    private String description;

    private Integer cylinders;

    private String origin;

    private LocalDate year;
}
