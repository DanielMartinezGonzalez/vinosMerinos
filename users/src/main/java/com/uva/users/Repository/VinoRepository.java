package com.uva.users.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uva.users.Model.Vino;

import jakarta.transaction.Transactional;

public interface VinoRepository extends JpaRepository<Vino, Integer> {

    @Query("SELECT v FROM Vino v WHERE v.denominacion = ?1 ORDER BY v.nombreComercial DESC")
    List<Vino> findByDenomincacionOrdenadoNombreDesc(String denominacion);

    @Query(value = "SELECT * FROM Vino WHERE denominacion = ?1 AND categoria = ?2", nativeQuery = true)
    List<Vino> findByDenominacionYCategoria(String denominacion, String categoria);

    Optional<Vino> findByNombreComercial(String nombre);

    List<Vino> findByPrecioBetween(Float precio1, Float precio2);

    boolean existsVinoByDenominacionAndCategoria(String denomiacion, String categoria);

    @Transactional
    // Long deleteByDenominacionAndCategoria(String denominacion, String categoria);
    List<Vino> deleteByDenominacionAndCategoria(String denominacion, String categoria);
}
