import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PieceDTO } from '../models/piece.dto';

@Injectable({
  providedIn: 'root'
})
export class PieceService {
  private api = 'http://localhost:8082/api/pieces';

  constructor(private http: HttpClient) {}

  getAll(): Observable<PieceDTO[]> {
    return this.http.get<PieceDTO[]>(this.api);
  }

  getById(id: number): Observable<PieceDTO> {
    return this.http.get<PieceDTO>(`${this.api}/${id}`);
  }

  create(piece: PieceDTO): Observable<PieceDTO> {
    return this.http.post<PieceDTO>(this.api, piece);
  }

  update(id: number, piece: PieceDTO): Observable<PieceDTO> {
    return this.http.put<PieceDTO>(`${this.api}/${id}`, piece);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
  getHierarchy(): Observable<PieceDTO[]> {
    return this.http.get<PieceDTO[]>(`${this.api}/hierarchie`);
  }

}
