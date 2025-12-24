import { Component, Input } from '@angular/core';
import { PieceDTO } from '../../models/piece.dto';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-piece-node',
  templateUrl: './piece-node.component.html',
  standalone: true,
  imports: [CommonModule]
})
export class PieceNodeComponent {
  @Input() piece!: PieceDTO;
}

