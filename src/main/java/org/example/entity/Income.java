package org.example.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "add_date")
    private LocalDate addDate;

    private String comment;

    private BigDecimal amount;

    public Income(String comment, BigDecimal amount) {
        this.comment = comment;
        this.amount = amount;
        this.addDate = LocalDate.now();
    }

}
