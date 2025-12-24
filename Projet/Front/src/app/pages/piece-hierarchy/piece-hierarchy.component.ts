import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PieceService } from '../../services/piece.service';
import { PieceDTO } from '../../models/piece.dto';
import { PieceNodeComponent } from '../piece-node/piece-node.component';


@Component({
  selector: 'app-piece-hierarchy',
  templateUrl: './piece-hierarchy.component.html',
  styleUrls: ['./piece-hierarchy.component.scss'],
  standalone: true,
  imports: [CommonModule, PieceNodeComponent]
})
export class PieceHierarchyComponent implements OnInit {
  hierarchy: PieceDTO[] = [];

  constructor(private pieceService: PieceService) {}


ngOnInit(): void {
  this.pieceService.getHierarchy().subscribe(data => {
    this.hierarchy = data;
    console.log('Hiérarchie chargée :', data); // ← utile pour debug
  });
}



}


