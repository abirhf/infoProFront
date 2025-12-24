import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClientService } from '../../../services/client.service';
import { ClientDTO } from '../../../models/client.dto';
import { Router, ActivatedRoute } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, RouterModule]
})
export class ClientFormComponent implements OnInit {
  form: FormGroup;
  clientId?: number;
  isEditMode = false;

  constructor(
    private fb: FormBuilder,
    private clientService: ClientService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      telephone: [''],
      email: ['', [Validators.required, Validators.email]],
      adresse: [''],
      type: ['']
    });
  }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.clientId = +idParam;
      this.isEditMode = true;

      this.clientService.getById(this.clientId).subscribe(client => {
        this.form.patchValue(client);
      });
    }
  }

  onSubmit(): void {
    console.log('Form submitted:', this.form.value);

    if (!this.form.valid) {
      alert('Formulaire invalide. Vérifie les champs obligatoires.');
      return;
    }

    const client: ClientDTO = this.form.value;

    if (this.isEditMode && this.clientId) {
      this.clientService.update(this.clientId, client).subscribe({
        next: () => {
          alert('Client modifié avec succès !');
          this.router.navigate(['/clients/list']);
        },
        error: err => {
          console.error('Erreur lors de la modification :', err);
          alert('Échec de la modification du client.');
        }
      });
    } else {
      this.clientService.create(client).subscribe({
        next: () => {
          alert('Client ajouté avec succès !');
          this.router.navigate(['/clients/list']);
        },
        error: err => {
          console.error('Erreur lors de l’ajout :', err);
          alert('Échec de l’ajout du client.');
        }
      });
    }
  }
}
