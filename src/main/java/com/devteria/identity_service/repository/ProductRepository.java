package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    // 1️⃣ Tìm sản phẩm có tên trùng khớp chính xác (bỏ qua hoa/thường)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
    Page<Product> findByExactName(@Param("name") String name, Pageable pageable);

    // 2️⃣ Tìm sản phẩm có chữ cái đầu trùng (bỏ qua hoa/thường)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT(:firstLetter, '%'))")
    Page<Product> findByFirstLetter(@Param("firstLetter") String firstLetter, Pageable pageable);


}
