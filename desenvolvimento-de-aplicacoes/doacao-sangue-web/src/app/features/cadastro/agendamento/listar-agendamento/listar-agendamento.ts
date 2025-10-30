import { Component, inject, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';

import { finalize } from 'rxjs';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatCardModule } from '@angular/material/card';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';

import { DialogAgendamento } from '../components/dialog-agendamento/dialog-agendamento';

import { AgendamentoService } from '../services/agendamento-service';

import { IAgendamento } from '../models/agendamento';

@Component({
  selector: 'gabi-listar-agendamento',
  imports: [
    DatePipe,
    MatProgressSpinnerModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
  ],
  templateUrl: './listar-agendamento.html',
  styleUrl: './listar-agendamento.scss',
})
export class ListarAgendamento implements OnInit {
  private readonly agendamentoService = inject(AgendamentoService);
  private readonly snackbar = inject(MatSnackBar);
  private readonly router = inject(Router);
  readonly dialog = inject(MatDialog);
  readonly carregando = signal(false);
  displayedColumns = ['id', 'dataHora', 'unidade', 'acoes'];
  dataSource = new MatTableDataSource<IAgendamento>([]);

  ngOnInit(): void {
    this.listarAgendamentos();
  }

  private listarAgendamentos(): void {
    this.carregando.set(true);
    this.agendamentoService
      .listarAgendamentos()
      .pipe(finalize(() => this.carregando.set(false)))
      .subscribe({
        next: (listaAgendamentos: IAgendamento[]) => this.dataSource.data = listaAgendamentos,
        error: (error: HttpErrorResponse) => this.mostrarSnackBar(error.message)
      });
  }

  visualizarDadosAgendamento(agendamento: IAgendamento): void {
    this
      .dialog
      .open(DialogAgendamento, {
        data: agendamento.doadores,
        disableClose: true
      });
  }

  navegarParaTelaCadastro(): void {
    this.router.navigate(['/cadastro/agendamento/cadastrar']);
  }

  editarAgendamento(idAgendamento: number): void {
    this.router.navigate([`/cadastro/agendamento/editar/${idAgendamento}`]);
  }

  apagarAgendamento(idAgendamento: number, indiceAgendamento: number): void {
    this.carregando.set(true);
    this.agendamentoService
      .apagarAgendamento(idAgendamento)
      .pipe(finalize(() => this.carregando.set(false)))
      .subscribe({
        next: () => {
          this.mostrarSnackBar('Agendamento apagado com sucesso!');
          this.removerAgendamentoTabela(indiceAgendamento);
        },
        error: (error: HttpErrorResponse) => this.mostrarSnackBar(error.message)
      });
  }

  private removerAgendamentoTabela(indiceAgendamento: number): void {
    const data = this.dataSource.data;
    data.splice(indiceAgendamento, 1);
    this.dataSource.data = [...data];
  }

  private mostrarSnackBar(mensagem: string): void {
    this.snackbar.open(
      mensagem,
      'x',
      {
        duration: 10000,
        horizontalPosition: 'end',
        verticalPosition: 'top'
      }
    )
  }
}
