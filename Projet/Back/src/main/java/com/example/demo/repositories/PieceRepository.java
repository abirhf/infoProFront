package com.example.demo.repositories;

import com.example.demo.entities.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece, Long> {

    boolean existsByReference(String reference);

    Piece findByReference(String reference);
}
