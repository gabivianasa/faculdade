import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { environment } from '../../../../../environments/environment.development';

import { IDoador, IDoadorCadastro, IDoadorListagem } from '../models/doador';

@Injectable({
  providedIn: 'root'
})
export class DoadorService {
  private http = inject(HttpClient);
  private readonly URL_API = environment.api;
  private readonly API_PATH = 'doador';

  listarDoadores(): Observable<IDoadorListagem[]> {
    return this.http
      .get<IDoadorListagem[]>(`${this.URL_API}${this.API_PATH}`);
  }

  listarDoadorPorId(idDoador: number): Observable<IDoador> {
    return this.http
      .get<IDoador>(`${this.URL_API}${this.API_PATH}/${idDoador}`);
  }

  cadastrarDoador(doador: IDoadorCadastro): Observable<IDoador> {
    return this.http
      .post<IDoador>(`${this.URL_API}${this.API_PATH}`, doador);
  }

  editarDoador(doador: IDoador): Observable<IDoador> {
    return this.http
      .put<IDoador>(`${this.URL_API}${this.API_PATH}/${doador.id}`, doador);
  }

  apagarDoador(idDoador: number): Observable<void> {
    return this.http
      .delete<void>(`${this.URL_API}${this.API_PATH}/${idDoador}`);
  }
}
