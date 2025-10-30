import { Component, inject, signal } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

import { finalize } from 'rxjs';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';

import { FormularioAgendamento } from "../components/formulario-agendamento/formulario-agendamento";

import { AgendamentoService } from '../services/agendamento-service';

import { IAgendamento, IAgendamentoCadastro } from '../models/agendamento';

@Component({
  selector: 'gabi-cadastrar-agendamento',
  imports: [
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    FormularioAgendamento,
  ],
  templateUrl: './cadastrar-agendamento.html',
  styleUrl: './cadastrar-agendamento.scss',
})
export class CadastrarAgendamento {
  private readonly formBuilder = inject(FormBuilder);
  private readonly router = inject(Router);
  private readonly agendamentoService = inject(AgendamentoService);
  private readonly matSnackbar = inject(MatSnackBar);
  readonly carregando = signal(false);
  readonly formGroup: FormGroup;

  constructor() {
    this.formGroup = this.formBuilder.group({
      dataHora: new FormControl(null, [Validators.required]),
      unidade: new FormControl(null, [Validators.required, Validators.maxLength(200)]),
      doadorIds: new FormControl(null, [Validators.required])
    });
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

  voltarParaPaginaAnterior(): void {
    this.router.navigate(['/cadastro/agendamento']);
  }

  cadastrarAgendamento(): void {
    this.carregando.set(true);

    const objRequest: IAgendamentoCadastro = {
      ...this.formGroup.value,
      doadorIds: [this.doadorIds.value.id]
    };

    this.agendamentoService
      .cadastrarAgendamento(objRequest)
      .pipe(finalize(() => this.carregando.set(false)))
      .subscribe({
        next: (resultado: IAgendamento) => {
          this.mostrarSnackBar('Agendamento cadastrado com sucesso!');
          this.router.navigate(['/cadastro/agendamento']);
        },
        error: (error: HttpErrorResponse) => this.mostrarSnackBar(error.message)
      })
  }

  private mostrarSnackBar(error: string): void {
    this.matSnackbar.open(
      error,
      'x',
      {
        duration: 10000,
        horizontalPosition: 'end',
        verticalPosition: 'top'
      }
    )
  }
}
