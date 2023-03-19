package org.example.service;

import java.math.BigDecimal;
import java.util.List;

import org.example.dao.IncomeDao;
import org.example.dto.IncomeDto;
import org.example.entity.Income;

public class IncomeService {
    private final IncomeDao incomeDao;

    public IncomeService(IncomeDao incomeDao) {
        this.incomeDao = incomeDao;
    }

    public void addIncome(BigDecimal amount, String comment) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            Income income = new Income(comment, amount);
            incomeDao.insert(income);
        }

    }

    public List<IncomeDto> getIncomes() {
        List<Income> incomes = incomeDao.findAll();
        return incomes.stream().map(i -> new IncomeDto(i.getId(), i.getAmount(), i.getComment(), i.getAddDate())).toList();
    }

    public void deleteById(int selectedId) {
        if (selectedId > 0) {
            incomeDao.deleteById(selectedId);
        } else {
            throw new IllegalArgumentException("id can not be lower than 0");
        }
    }
}
