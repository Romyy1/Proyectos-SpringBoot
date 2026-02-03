package com.romy.ticketing.Repository;

import com.romy.ticketing.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
