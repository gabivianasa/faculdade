import { Component, inject, OnInit, signal } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';

import { finalize } from 'rxjs';

import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';

import { DoadorService } from '../../services/doador-service';

import { IDoador } from '../../models/doador';

@Component({
  selector: 'gabi-dialog-dados-doador',
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatListModule,
    MatIconModule,
    MatDividerModule,
    DatePipe
  ],
  templateUrl: './dialog-dados-doador.html',
  styleUrl: './dialog-dados-doador.scss',
})
export class DialogDadosDoador implements OnInit {
  private readonly doadorService = inject(DoadorService);
  private readonly snackbar = inject(MatSnackBar);
  readonly data = inject(MAT_DIALOG_DATA);
  readonly carregando = signal(true);
  readonly dadosDoador = signal<IDoador | null>(null);

  ngOnInit(): void {
    this.buscarDadosDoador();
  }

  buscarDadosDoador(): void {
    this.doadorService
      .listarDoadorPorId(this.data.idDoador)
      .pipe(finalize(() => this.carregando.set(false)))
      .subscribe({
        next: (doador: IDoador) => this.dadosDoador.set(doador),
        error: (error: HttpErrorResponse) => this.mostrarSnackBar(error.message)
      })
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
