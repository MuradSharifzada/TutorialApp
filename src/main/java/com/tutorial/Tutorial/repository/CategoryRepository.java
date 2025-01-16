package com.tutorial.Tutorial.repository;

import com.tutorial.Tutorial.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


//    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.tutorials WHERE c.id = :id")
//    Category findCategoryWithTutorials(@Param("id") Long categoryId);

    boolean existsByCategoryName(String name);

    @Query("SELECT c FROM Category c WHERE LOWER(c.categoryName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Category> findByCategoryNameContainingIgnoreCase(@Param("name") String name);
}
