import { Component, inject, Input, OnInit, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { finalize } from 'rxjs';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { MatDatepickerModule } from '@angular/material/datepicker';

import { MatSnackBar } from '@angular/material/snack-bar';

import { DoadorService } from '../../../doador/services/doador-service';

import { IDoadorListagem } from '../../../doador/models/doador';

@Component({
  selector: 'gabi-formulario-agendamento',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatTimepickerModule,
    MatDatepickerModule
  ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './formulario-agendamento.html',
  styleUrl: './formulario-agendamento.scss',
})
export class FormularioAgendamento implements OnInit {
  @Input() formGroup!: FormGroup;
  private readonly doadorService = inject(DoadorService);
  private readonly snackbar = inject(MatSnackBar);
  readonly listaDoadores = signal<IDoadorListagem[]>([]);
  readonly carregando = signal(true);
  readonly dataMinima = signal(new Date());

  ngOnInit(): void {
    this.listarDoadores();
    const dataAgora = new Date();
    dataAgora.setHours(0, 0, 0, 0);
    this.dataHora.setValue(dataAgora);
  }

  get dataHora(): FormControl {
    return this.formGroup.get('dataHora') as FormControl;
  }

  get unidade(): FormControl {
    return this.formGroup.get('unidade') as FormControl;
  }

  get doadorIds(): FormControl {
    return this.formGroup.get('doadorIds') as FormControl;
  }

  private listarDoadores(): void {
    this.doadorService
      .listarDoadores()
      .pipe(finalize(() => this.carregando.set(false)))
      .subscribe({
        next: (doadores: IDoadorListagem[]) => this.listaDoadores.set(doadores),
        error: (error: HttpErrorResponse) => this.mostrarSnackBar(error.message)
      });
  }

  atualizarData(data: Date) {
    const valorAtual = this.dataHora?.value ?? new Date();
    const valorAtualizado = new Date(data);
    valorAtualizado.setHours(valorAtual.getHours(), valorAtual.getMinutes(), 0, 0);
    this.dataHora?.setValue(valorAtualizado);
  }

  compararDoador(a: any, b: any): boolean {
    return a && b ? a.id === b.id : a === b;
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
