import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // ← nécessaire pour *ngFor
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ClientService } from '../../../services/client.service';
import { ClientDTO } from '../../../models/client.dto';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule] // ← AJOUT ici
})
export class ClientListComponent implements OnInit {
  clients: ClientDTO[] = [];
  searchTerm: string = '';

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.clientService.getAll().subscribe(data => {
      this.clients = data;
    });
  }

  filteredClients(): ClientDTO[] {
    const term = this.searchTerm.toLowerCase();
    return this.clients.filter(client =>
      client.nom.toLowerCase().includes(term) ||
      client.prenom.toLowerCase().includes(term) ||
      client.email.toLowerCase().includes(term)
    );
  }
  confirmDelete(id: number): void {
  if (confirm('Voulez-vous vraiment supprimer ce client ?')) {
    this.clientService.delete(id).subscribe(() => {
      this.clients = this.clients.filter(c => c.id !== id);
    });
  }
}

}
