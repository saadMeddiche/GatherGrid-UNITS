package com.gathergrid.service;

import com.gathergrid.entities.Categorie;
import com.gathergrid.entities.Response;
import com.gathergrid.repository.CategorieRepository;

import java.util.List;

public class CategoryService {
    private final CategorieRepository categoryRepository;

    public CategoryService(CategorieRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Response getCategories() {
        List<Categorie> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return new Response("No Categories Found", 404);
        } else {
            return new Response("Categories Found", categories, 200);
        }
    }


}
