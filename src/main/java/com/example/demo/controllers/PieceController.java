package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PieceDTO;
import com.example.demo.dto.FichierImporteeDTO;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Piece;
import com.example.demo.repositories.CategorieRepository;
import com.example.demo.repositories.PieceRepository;
import com.example.demo.services.PieceService;

@RestController
@RequestMapping("/api/pieces")
@CrossOrigin(origins = "*")
public class PieceController {

    @Autowired
    private PieceService pieceService;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private PieceRepository pieceRepository;

    // 🔹 Liste des pièces en DTO
    @GetMapping
    public ResponseEntity<List<PieceDTO>> getAll() {
        List<PieceDTO> pieces = pieceService.getAllPieceDTOs();
        return ResponseEntity.ok(pieces);
    }

    // 🔹 Détail d'une pièce en DTO
    @GetMapping("/{id}")
    public ResponseEntity<PieceDTO> getById(@PathVariable Long id) {
        PieceDTO dto = pieceService.getPieceDTOById(id);
        return (dto != null) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // 🔹 Création
    @PostMapping
    public ResponseEntity<Piece> create(@RequestBody Piece piece) {
        Piece saved = pieceService.savePiece(piece);
        return ResponseEntity.ok(saved);
    }

    // 🔹 Mise à jour
    @PutMapping("/{id}")
    public ResponseEntity<Piece> update(@PathVariable Long id, @RequestBody Piece piece) {
        piece.setId(id);
        Piece updated = pieceService.savePiece(piece);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Suppression
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pieceService.deletePiece(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Filtrer par catégorie
    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<PieceDTO>> getByCategorie(@PathVariable Long categorieId) {
        List<PieceDTO> pieces = pieceService.getPiecesByCategorie(categorieId);
        return ResponseEntity.ok(pieces);
    }

    // 🔹 Hiérarchie complète
    @GetMapping("/hierarchie")
    public ResponseEntity<List<PieceDTO>> getHierarchie() {
        return ResponseEntity.ok(pieceService.getHierarchie());
    }

    // 🔹 Import d'une pièce depuis Excel/CSV
    @PostMapping("/import")
    public ResponseEntity<?> importPiece(@RequestBody FichierImporteeDTO dto) {
        // Vérifier si la référence existe déjà
        if (pieceRepository.existsByReference(dto.getReference())) {
            return ResponseEntity.status(409).body("Référence déjà existante : " + dto.getReference());
        }

        Piece piece = new Piece();
        piece.setReference(dto.getReference());
        piece.setNom(dto.getNom());

        // Categorie
        Categorie cat = categorieRepository.findByNom(dto.getCategorieNom());
        if (cat == null) {
            cat = new Categorie();
            cat.setNom(dto.getCategorieNom());
            cat = categorieRepository.save(cat);
        }
        piece.setCategorie(cat);

        // Parent
        if (dto.getParentId() != null) {
            Piece parent = pieceRepository.findByReference(dto.getParentId().toString());
            if (parent != null) {
                piece.setParent(parent);
            }
        }

        pieceRepository.save(piece);
        return ResponseEntity.ok().build();
    }
}
