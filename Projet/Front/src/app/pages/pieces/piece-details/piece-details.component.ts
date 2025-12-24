import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { PieceService } from '../../../services/piece.service';
import { PieceDTO } from '../../../models/piece.dto';

@Component({
  selector: 'app-piece-details',
  templateUrl: './piece-details.component.html',
  styleUrls: ['./piece-details.component.scss'],
  standalone: true,
  imports: [CommonModule, RouterModule]
})
export class PieceDetailsComponent implements OnInit {
  piece?: PieceDTO;

  constructor(
    private route: ActivatedRoute,
    private pieceService: PieceService
  ) {}
ngOnInit(): void {
  const idParam = this.route.snapshot.paramMap.get('id');
  if (!idParam) {
    console.warn('Aucun ID trouvé dans l’URL.');
    return;
  }

  const id = +idParam;
  console.log('ID reçu pour détails :', id);

  this.pieceService.getById(id).subscribe({
    next: data => {
      if (data) {
        console.log('Détails reçus :', data);
        this.piece = data;
      } else {
        console.warn('La pièce est introuvable.');
        alert('❌ Pièce non trouvée.');
      }
    },
    error: err => {
      console.error('Erreur lors du chargement des détails :', err);
      alert('❌ Impossible de charger les détails de la pièce.');
    }
  });
}


}
