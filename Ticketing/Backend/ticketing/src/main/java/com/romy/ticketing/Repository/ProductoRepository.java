package com.romy.ticketing.Repository;

import com.romy.ticketing.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends JpaRepository<Product,Long> {
}
