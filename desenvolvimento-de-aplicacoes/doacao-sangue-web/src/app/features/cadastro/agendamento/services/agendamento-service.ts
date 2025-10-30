import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { environment } from '../../../../../environments/environment.development';

import { IAgendamento, IAgendamentoCadastro } from '../models/agendamento';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {
  private http = inject(HttpClient);
  private readonly URL_API = environment.api;
  private readonly API_PATH = 'agendamento';

  listarAgendamentos(): Observable<IAgendamento[]> {
    return this.http
      .get<IAgendamento[]>(`${this.URL_API}${this.API_PATH}`);
  }

  retornarAgendamentoPorId(idAgendamento: number): Observable<IAgendamento> {
    return this.http
      .get<IAgendamento>(`${this.URL_API}${this.API_PATH}/${idAgendamento}`);
  }

  cadastrarAgendamento(agendamento: IAgendamentoCadastro): Observable<IAgendamento> {
    return this.http
      .post<IAgendamento>(`${this.URL_API}${this.API_PATH}`, agendamento);
  }

  editarAgendamento(agendamento: IAgendamento): Observable<IAgendamento> {
    return this.http
      .put<IAgendamento>(`${this.URL_API}${this.API_PATH}/${agendamento.id}`, agendamento);
  }

  apagarAgendamento(idAgendamento: number): Observable<void> {
    return this.http
      .delete<void>(`${this.URL_API}${this.API_PATH}/${idAgendamento}`);
  }
}
