package org.example.service;

import java.util.List;

import org.example.dao.CategoryDao;
import org.example.entity.Category;

public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    public void addCategory(String categoryName) {
        if (categoryName.length() > 2 && !isCategoryExists(categoryName)) {
            Category category = new Category(categoryName);
            categoryDao.insert(category);
        } else {
            throw new IllegalArgumentException("Category name can not be shorter than 3 or category name already exists.");
        }
    }

    private boolean isCategoryExists(String categoryName) {
        return categoryDao.findByName(categoryName) != null;
    }

    public List<String> getCategoryNames() {
        return categoryDao.findCategoryNames();
    }

    public void deleteByName(String categoryName) {
        if (isCategoryExists(categoryName)) {
            categoryDao.deleteByName(categoryName);
        } else {
            throw new IllegalArgumentException("Category with name " + categoryName + " not exists.");
        }
    }
}
