import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ClientService } from '../../../services/client.service';
import { ClientDTO } from '../../../models/client.dto';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.scss'],
  standalone: true,
  imports: [CommonModule,RouterModule]
})
export class ClientDetailsComponent implements OnInit {
  clientId!: number;
  client!: ClientDTO;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService
  ) {}

  ngOnInit(): void {
    this.clientId = Number(this.route.snapshot.paramMap.get('id'));
    this.clientService.getById(this.clientId).subscribe(data => {
      this.client = data;
    });
  }
}
