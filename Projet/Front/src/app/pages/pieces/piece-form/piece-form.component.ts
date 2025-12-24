import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PieceService } from '../../../services/piece.service';
import { PieceDTO } from '../../../models/piece.dto';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-piece-form',
  templateUrl: './piece-form.component.html',
  styleUrls: ['./piece-form.component.scss'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule]
})
export class PieceFormComponent implements OnInit {
  form: FormGroup;
  pieceId?: number;
  isEditMode = false;

  constructor(
    private fb: FormBuilder,
    private pieceService: PieceService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      nom: ['', Validators.required],
      reference: ['', Validators.required],
      description: [''],
      prix: [0, Validators.required]
    });
  }

 ngOnInit(): void {
  const idParam = this.route.snapshot.paramMap.get('id');
  if (idParam) {
    this.pieceId = +idParam;
    this.isEditMode = true;

    this.pieceService.getById(this.pieceId).subscribe({
      next: piece => {
        console.log('Données reçues pour modification :', piece);
        if (piece) {
          this.form.patchValue({
            nom: piece.nom,
            reference: piece.reference,
            description: piece.description,
            prix: piece.prix
          });
        }
      },
      error: err => {
        console.error('Erreur lors du chargement de la pièce :', err);
        alert('❌ Impossible de charger la pièce.');
      }
    });
  }
}

onSubmit(): void {
  if (!this.form.valid) {
    alert('Formulaire invalide.');
    return;
  }

  const piece: PieceDTO = this.form.value;

  if (this.isEditMode && this.pieceId) {
    this.pieceService.update(this.pieceId, piece).subscribe({
      next: () => {
        console.log('✅ Mise à jour réussie');
        alert('✅ Pièce modifiée avec succès !');
        this.router.navigate(['/pieces/list']);
      },
      error: err => {
        console.error('Erreur lors de la modification :', err);
        alert('❌ Échec de la modification.');
      }
    });
  } else {
    this.pieceService.create(piece).subscribe({
      next: () => {
        console.log('✅ Création réussie');
        alert('✅ Pièce ajoutée avec succès !');
        this.router.navigate(['/pieces/list']);
      },
      error: err => {
        console.error('Erreur lors de l’ajout :', err);
        alert('❌ Échec de l’ajout.');
      }
    });
  }
}
}
