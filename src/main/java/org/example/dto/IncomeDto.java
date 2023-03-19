package org.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IncomeDto {
    private long id;
    private BigDecimal amount;
    private String comment;
    private LocalDate addDate;

    @Override
    public String toString() {
        return "IncomeDto{" + "id=" + id + ", amount=" + amount + "zł , comment='" + comment + '\'' + ", addDate=" + addDate + '}';
    }
}
