import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CategorieDTO } from '../../../models/category.dto';
import { FormsModule } from '@angular/forms';

import { CategorieService } from '../../../services/categorie.service';
@Component({
  selector: 'app-categorie-list',
  templateUrl: './categorie-list.component.html',
  styleUrls: ['./categorie-list.component.scss'],
  standalone: true,
  imports: [CommonModule, RouterModule,FormsModule]
})
export class CategorieListComponent implements OnInit {
  categories: CategorieDTO[] = [];
  searchTerm = '';

  constructor(private categorieService: CategorieService) {}

  ngOnInit(): void {
      console.log('CategorieListComponent chargé');
    this.categorieService.getAll().subscribe(data => {
      this.categories = data;
    });
  }

  filteredCategories(): CategorieDTO[] {
    const term = this.searchTerm.toLowerCase();
    return this.categories.filter(c => c.nom.toLowerCase().includes(term));
  }

  confirmDelete(id: number): void {
    if (confirm('Supprimer cette catégorie ?')) {
      this.categorieService.delete(id).subscribe(() => {
        this.categories = this.categories.filter(c => c.id !== id);
      });
    }
  }
}
