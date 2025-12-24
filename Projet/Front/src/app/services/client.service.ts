import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClientDTO } from '../models/client.dto';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ClientService {
  private api = 'http://localhost:8082/api/clients';

  constructor(private http: HttpClient) {}

  getAll(): Observable<ClientDTO[]> {
    return this.http.get<ClientDTO[]>(this.api);
  }
  create(client: ClientDTO): Observable<ClientDTO> {
  return this.http.post<ClientDTO>(this.api, client);
}
getById(id: number): Observable<ClientDTO> {
  return this.http.get<ClientDTO>(`${this.api}/${id}`);
}
delete(id: number): Observable<void> {
  return this.http.delete<void>(`${this.api}/${id}`);
}
update(id: number, client: ClientDTO): Observable<ClientDTO> {
  return this.http.put<ClientDTO>(`${this.api}/${id}`, client);
}

}
