import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CategorieDTO } from '../models/category.dto';
@Injectable({
  providedIn: 'root'
})
export class CategorieService {
  private api = 'http://localhost:8082/api/categories';

  constructor(private http: HttpClient) {}

  getAll(): Observable<CategorieDTO[]> {
    return this.http.get<CategorieDTO[]>(this.api);
  }

  getById(id: number): Observable<CategorieDTO> {
    return this.http.get<CategorieDTO>(`${this.api}/${id}`);
  }

  create(categorie: CategorieDTO): Observable<CategorieDTO> {
    return this.http.post<CategorieDTO>(this.api, categorie);
  }

  update(id: number, categorie: CategorieDTO): Observable<CategorieDTO> {
    return this.http.put<CategorieDTO>(`${this.api}/${id}`, categorie);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
