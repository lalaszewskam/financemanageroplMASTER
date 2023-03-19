package org.example;


import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.example.dao.CategoryDao;
import org.example.dao.IncomeDao;
import org.example.dto.IncomeDto;
import org.example.service.CategoryService;
import org.example.service.IncomeService;
import org.example.utils.DBConnection;

public class Main {
    private static final CategoryDao categoryDao = new CategoryDao();
    private static final CategoryService categoryService = new CategoryService(categoryDao);
    private static final IncomeDao incomeDao = new IncomeDao();
    private static final IncomeService incomeService = new IncomeService(incomeDao);

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in).useLocale(Locale.ROOT);

        while (true) {
            System.out.println("Type operation");
            System.out.println("2 - add income");
            System.out.println("4 - delete income");
            System.out.println("10 - print incomes");
            System.out.println("12 - add new category");
            System.out.println("13 - delete category");

            int selectedOperation = in.nextInt();

            switch (selectedOperation) {
                case 0 -> {
                    DBConnection.closeSessionFactory();
                    System.exit(0);
                }
                case 2 -> {
                    System.out.println("Type income amount");
                    BigDecimal amount = in.nextBigDecimal();
                    System.out.println("Type income comment");
                    in.nextLine();
                    String comment = in.nextLine();
                    incomeService.addIncome(amount, comment);
                }
                case 4 -> {
                    List<IncomeDto> incomes = incomeService.getIncomes();
                    System.out.println("Select income id to be deleted");
                    incomes.forEach(System.out::println);
                    int selectedId = in.nextInt();
                    try {
                        incomeService.deleteById(selectedId);
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 10 -> {
                    List<IncomeDto> incomes = incomeService.getIncomes();
                    incomes.forEach(System.out::println);
                }
                case 12 -> {
                    System.out.println("Type category name");
                    in.nextLine();
                    String categoryName = in.nextLine();
                    try {
                        categoryService.addCategory(categoryName);
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 13 -> {
                    List<String> categoryNames = categoryService.getCategoryNames();
                    String result = String.join(", ", categoryNames);
                    System.out.print("Select category [");
                    System.out.print(result);
                    System.out.println("]");

                    System.out.println("Type category name");
                    in.nextLine();
                    String categoryName = in.nextLine();
                    categoryService.deleteByName(categoryName);
                }
            }
        }
    }
}