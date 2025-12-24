import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { PieceService } from '../../../services/piece.service';
import { PieceDTO } from '../../../models/piece.dto';

@Component({
  selector: 'app-piece-list',
  templateUrl: './piece-list.component.html',
  styleUrls: ['./piece-list.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule]
})
export class PieceListComponent implements OnInit {
  pieces: PieceDTO[] = [];
  searchTerm: string = '';

  constructor(private pieceService: PieceService) {}

  ngOnInit(): void {
    this.pieceService.getAll().subscribe(data => {
          console.log('Pièces chargées :', data); // ← Ajoute ce log pour tester
      this.pieces = data;
    });
  }

  filteredPieces(): PieceDTO[] {
    const term = this.searchTerm.toLowerCase();
    return this.pieces.filter(piece =>
      piece.nom.toLowerCase().includes(term) ||
      piece.reference.toLowerCase().includes(term)
    );
    
  }
  

  confirmDelete(id: number): void {
    if (confirm('Supprimer cette pièce ?')) {
      this.pieceService.delete(id).subscribe(() => {
        this.pieces = this.pieces.filter(p => p.id !== id);
      });
    }
  }
}
