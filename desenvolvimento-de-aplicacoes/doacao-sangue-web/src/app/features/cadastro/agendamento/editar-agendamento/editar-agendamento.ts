import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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
  selector: 'gabi-editar-agendamento',
  imports: [
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    FormularioAgendamento,
  ],
  templateUrl: './editar-agendamento.html',
  styleUrl: './editar-agendamento.scss',
})
export class EditarAgendamento implements OnInit {
  private readonly formBuilder = inject(FormBuilder);
  private readonly router = inject(Router);
  private readonly agendamentoService = inject(AgendamentoService);
  private readonly matSnackbar = inject(MatSnackBar);
  private readonly activatedRouter = inject(ActivatedRoute);
  private readonly agendamento = signal<IAgendamento | undefined>(undefined);
  readonly carregando = signal(false);
  readonly formGroup: FormGroup;

  constructor() {
    this.formGroup = this.formBuilder.group({
      dataHora: new FormControl(null, [Validators.required]),
      unidade: new FormControl(null, [Validators.required, Validators.maxLength(200)]),
      doadorIds: new FormControl(null, [Validators.required])
    });
  }

  ngOnInit(): void {
    this.verificarIdOuVoltarPaginaAnterior();
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

  private verificarIdOuVoltarPaginaAnterior(): void {
    const id = Number(this.activatedRouter.snapshot.paramMap.get('id'));

    if (Number.isNaN(id)) {
      this.router.navigate(['/cadastro/doador']);
      return;
    }

    this.buscarDadosAgendamento(id);
  }

  private buscarDadosAgendamento(idAgendamento: number): void {
    this.agendamentoService
      .retornarAgendamentoPorId(idAgendamento)
      .subscribe({
        next: (resultado: IAgendamento) => {
          this.agendamento.set(resultado);
          this.atualizarFormulario(resultado);
        },
        error: (error: HttpErrorResponse) => {
          if (error.status === 404) {
            this.router.navigate(['/cadastro/agendamento']);
          } else {
            this.mostrarSnackBar(error.message);
          }
        }
      });
  }

  private atualizarFormulario(agendamento: IAgendamento): void {
    this.formGroup.patchValue({
      dataHora: agendamento.dataHora,
      unidade: agendamento.unidade,
      doadorIds: {
        id: agendamento.doadores[0].id
      }
    });
  }

  voltarParaPaginaAnterior(): void {
    this.router.navigate(['/cadastro/agendamento']);
  }

  atualizarAgendamento(): void {
    this.carregando.set(true);

    const objRequest: IAgendamento = {
      ...this.formGroup.value,
      doadorIds: [this.doadorIds.value],
      id: this.agendamento()?.id
    };

    this.agendamentoService
      .editarAgendamento(objRequest)
      .pipe(finalize(() => this.carregando.set(false)))
      .subscribe({
        next: (resultado: IAgendamento) => {
          this.mostrarSnackBar('Agendamento atualizado com sucesso!');
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
