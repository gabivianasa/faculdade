import { Component, inject, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

import { finalize } from 'rxjs';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatCardModule } from '@angular/material/card';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';

import { IDoadorListagem } from '../models/doador';

import { DoadorService } from '../services/doador-service';

import { DialogDadosDoador } from '../components/dialog-dados-doador/dialog-dados-doador';

@Component({
  selector: 'gabi-listar-doador',
  imports: [
    MatProgressSpinnerModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule
  ],
  templateUrl: './listar-doador.html',
  styleUrl: './listar-doador.scss',
})
export class ListarDoador implements OnInit {
  private readonly doadorService = inject(DoadorService);
  private readonly snackbar = inject(MatSnackBar);
  private readonly router = inject(Router);
  readonly dialog = inject(MatDialog);
  readonly carregando = signal(false);
  displayedColumns = ['id', 'nome', 'acoes'];
  dataSource = new MatTableDataSource<IDoadorListagem>([]);

  ngOnInit(): void {
    this.listarDoadores();
  }

  private listarDoadores(): void {
    this.carregando.set(true);
    this.doadorService
      .listarDoadores()
      .pipe(finalize(() => this.carregando.set(false)))
      .subscribe({
        next: (listaDoadores: IDoadorListagem[]) => this.dataSource.data = listaDoadores,
        error: (error: HttpErrorResponse) => this.mostrarSnackBar(error.message)
      });
  }

  visualizarDadosDoador(idDoador: number): void {
    this
      .dialog
      .open(DialogDadosDoador, {
        data: { idDoador },
        disableClose: true
      });
  }

  navegarParaTelaCadastro(): void {
    this.router.navigate(['/cadastro/doador/cadastrar']);
  }

  editarDoador(idDoador: number): void {
    this.router.navigate([`/cadastro/doador/editar/${idDoador}`]);
  }

  apagarDoador(idDoador: number, indiceDoador: number): void {
    this.carregando.set(true);
    this.doadorService
      .apagarDoador(idDoador)
      .pipe(finalize(() => this.carregando.set(false)))
      .subscribe({
        next: () => {
          this.mostrarSnackBar('Doador apagado com sucesso!');
          this.removerDoadorTabela(indiceDoador);
        },
        error: (error: HttpErrorResponse) => this.mostrarSnackBar(error.message)
      });
  }

  private removerDoadorTabela(indiceDoador: number): void {
    const data = this.dataSource.data;
    data.splice(indiceDoador, 1);
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
