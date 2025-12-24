import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CategorieService } from '../../../services/categorie.service';
@Component({
  selector: 'app-categorie-form',
  templateUrl: './categorie-form.component.html',
  styleUrls: ['./categorie-form.component.scss'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule]
})
export class CategorieFormComponent implements OnInit {
  form: FormGroup;
  categorieId?: number;
  isEditMode = false;

  constructor(
    private fb: FormBuilder,
    private categorieService: CategorieService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      nom: ['', Validators.required],
      arborescenceId: [null]
    });
  }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.categorieId = +idParam;
      this.isEditMode = true;
      this.categorieService.getById(this.categorieId).subscribe(data => {
        this.form.patchValue(data);
      });
    }
  }
successMessage = '';

onSubmit(): void {
  if (!this.form.valid) return;

  const categorie = this.form.value;

  const action = this.isEditMode
    ? this.categorieService.update(this.categorieId!, categorie)
    : this.categorieService.create(categorie);

  action.subscribe({
    next: () => {
      this.successMessage = this.isEditMode
        ? '✅ Catégorie modifiée avec succès !'
        : '✅ Catégorie ajoutée avec succès !';

      setTimeout(() => this.router.navigate(['/categories/list']), 1500);
    },
    error: err => {
      console.error('❌ Erreur :', err);
      alert('❌ Échec de l’opération.');
    }
  });
}

  
}
