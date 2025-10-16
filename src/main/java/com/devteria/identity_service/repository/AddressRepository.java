package com.devteria.identity_service.repository;

import com.devteria.identity_service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

        // Dùng JPQL: lấy tất cả Address theo username của User
        @Query("""
        SELECT a
        FROM Address a
        JOIN a.customer c
        JOIN c.user u
        WHERE u.name = :username
    """)
        List<Address> findAllByUserName(@Param("username") String username);
    @Query("""
    SELECT a
    FROM Address a
    JOIN a.customer c
    JOIN c.user u
    WHERE a.addressId = :addressId AND u.name = :username
""")
    Optional<Address> findByIdAndUserName(@Param("addressId") Long addressId,
                                          @Param("username") String username);
}
