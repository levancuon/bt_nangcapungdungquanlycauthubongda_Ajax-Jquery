package org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.repository;



import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IPlayerRepo extends CrudRepository<Player,Long>, PagingAndSortingRepository<Player,Long> {

    Page<Player> findAllByNameContaining(String search, Pageable pageable);
    @Query("SELECT p FROM Player p WHERE " +
            "(:name IS NULL OR p.name like %:name%) AND " +
            "(:dobMin IS NULL OR p.dob >= :dobMin) AND " +
            "(:dobMax IS NULL OR p.dob <= :dobMax) ")
    Page<Player> findAll(
            Pageable pageable,
            @Param("name") String name,
            @Param("dobMin") LocalDate dobMin,
            @Param("dobMax") LocalDate dobMax

    );
    Long countByStatus(String status);
    Page<Player> findByNameContainingAndDobBetween(Pageable pageable, String name, LocalDate dobMin, LocalDate dobMax);
}
