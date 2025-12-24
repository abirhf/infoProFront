package com.example.demo.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PieceDTO;
import com.example.demo.entities.Piece;
import com.example.demo.repositories.PieceRepository;

@Service
public class PieceService {

    @Autowired
    private PieceRepository pieceRepository;

    // 🔹 Liste des pièces (entités, avec chargement des sous-pièces si nécessaire)
    @Transactional
    public List<Piece> getAllPieces() {
        List<Piece> pieces = pieceRepository.findAll();
        pieces.forEach(piece -> {
            if (piece.getSousPieces() != null) {
                piece.getSousPieces().size(); // force le chargement LAZY
            }
        });
        return pieces;
    }

    // 🔹 Liste des pièces en DTO (plat)
    public List<PieceDTO> getAllPieceDTOs() {
        return pieceRepository.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    // 🔹 Détail d'une pièce en DTO
    public PieceDTO getPieceDTOById(Long id) {
        Piece piece = pieceRepository.findById(id).orElse(null);
        return (piece != null) ? mapToDTO(piece) : null;
    }

    // 🔹 Détail d'une pièce en entité
    public Piece getPieceById(Long id) {
        return pieceRepository.findById(id).orElse(null);
    }

    // 🔹 Création ou mise à jour
    public Piece savePiece(Piece piece) {
        return pieceRepository.save(piece);
    }

    // 🔹 Suppression
    public void deletePiece(Long id) {
        pieceRepository.deleteById(id);
    }

    // 🔹 Filtrer par catégorie
    public List<PieceDTO> getPiecesByCategorie(Long categorieId) {
        return pieceRepository.findAll().stream()
            .filter(p -> p.getCategorie() != null && p.getCategorie().getId().equals(categorieId))
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    // 🔹 Construire la hiérarchie complète
    public List<PieceDTO> getHierarchie() {
        List<Piece> all = pieceRepository.findAll();
        Map<Long, PieceDTO> map = new HashMap<>();

        // 1. Mapper toutes les pièces en DTO
        for (Piece p : all) {
            PieceDTO dto = mapToDTO(p);
            dto.setEnfants(new ArrayList<>());
            map.put(dto.getId(), dto);
        }

        // 2. Relier les enfants à leurs parents
        List<PieceDTO> racines = new ArrayList<>();
        for (PieceDTO dto : map.values()) {
            if (dto.getParentId() != null && map.containsKey(dto.getParentId())) {
                map.get(dto.getParentId()).getEnfants().add(dto);
            } else {
                racines.add(dto);
            }
        }

        return racines;
    }

    // 🔧 Mapper une entité vers un DTO
    private PieceDTO mapToDTO(Piece piece) {
        PieceDTO dto = new PieceDTO();
        dto.setId(piece.getId());
        dto.setNom(piece.getNom());
        dto.setReference(piece.getReference());
        dto.setDescription(piece.getDescription());
        dto.setModeleVehicule(piece.getModeleVehicule());
        dto.setGarantie(piece.getGarantie());
        dto.setDateLivraison(piece.getDateLivraison());
        dto.setPrix(piece.getPrix());
        dto.setImage(piece.getImage());

        if (piece.getCategorie() != null) {
            dto.setCategorieId(piece.getCategorie().getId());
            dto.setCategorieNom(piece.getCategorie().getNom()); // ← utile pour affichage
        }

        if (piece.getParent() != null) {
            dto.setParentId(piece.getParent().getId());
            dto.setParentNom(piece.getParent().getNom()); // ← utile pour affichage
        }

        return dto;
    }
}
