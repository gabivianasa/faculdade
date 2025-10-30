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

import { FormularioDoador } from '../components/formulario-doador/formulario-doador';

import { DoadorService } from '../services/doador-service';

import { IDoador } from '../models/doador';

@Component({
  selector: 'gabi-editar-doador',
  imports: [
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    FormularioDoador
  ],
  templateUrl: './editar-doador.html',
  styleUrl: './editar-doador.scss',
})
export class EditarDoador implements OnInit {
  private readonly formBuilder = inject(FormBuilder);
  private readonly router = inject(Router);
  private readonly doadorService = inject(DoadorService);
  private readonly matSnackbar = inject(MatSnackBar);
  private readonly activatedRouter = inject(ActivatedRoute);
  private readonly doador = signal<IDoador | undefined>(undefined);
  readonly carregando = signal(false);
  readonly formGroup: FormGroup;

  constructor() {
    this.formGroup = this.formBuilder.group({
      nome: new FormControl(null, [Validators.required, Validators.maxLength(200)]),
      email: new FormControl(null, [Validators.required, Validators.maxLength(200), Validators.email]),
      dataNascimento: new FormControl(null, [Validators.required]),
      sexo: new FormControl('F'),
      tipoSanguineo: new FormControl(null, [Validators.required]),
      doencas: new FormControl(null, [Validators.required, Validators.maxLength(255)]),
      peso: new FormControl(null, [Validators.required, Validators.min(0.01)]),
      altura: new FormControl(null, [Validators.required, Validators.min(0.01)]),
    });
  }

  ngOnInit(): void {
    this.verificarIdOuVoltarPaginaAnterior();
  }

  get nome(): FormControl {
    return this.formGroup.get('nome') as FormControl;
  }

  get email(): FormControl {
    return this.formGroup.get('email') as FormControl;
  }

  get dataNascimento(): FormControl {
    return this.formGroup.get('dataNascimento') as FormControl;
  }

  get sexo(): FormControl {
    return this.formGroup.get('sexo') as FormControl;
  }

  get tipoSanguineo(): FormControl {
    return this.formGroup.get('tipoSanguineo') as FormControl;
  }

  get doencas(): FormControl {
    return this.formGroup.get('doencas') as FormControl;
  }

  get altura(): FormControl {
    return this.formGroup.get('altura') as FormControl;
  }

  get peso(): FormControl {
    return this.formGroup.get('peso') as FormControl;
  }

  private verificarIdOuVoltarPaginaAnterior(): void {
    const id = Number(this.activatedRouter.snapshot.paramMap.get('id'));

    if (Number.isNaN(id)) {
      this.router.navigate(['/cadastro/doador']);
      return;
    }

    this.buscarDadosDoador(id);
  }

  private buscarDadosDoador(idDoador: number): void {
    this.doadorService
      .listarDoadorPorId(idDoador)
      .subscribe({
        next: (resultado: IDoador) => {
          this.doador.set(resultado);
          this.atualizarFormulario(resultado);
        },
        error: (error: HttpErrorResponse) => {
          if (error.status === 404) {
            this.router.navigate(['/cadastro/doador']);
          } else {
            this.mostrarSnackBar(error.message);
          }
        }
      });
  }

  private atualizarFormulario(doador: IDoador): void {
    this.formGroup.patchValue({
      nome: doador.nome,
      email: doador.email,
      dataNascimento: this.formatarDataParaDatepicker(doador.dataNascimento),
      sexo: doador.sexo,
      tipoSanguineo: doador.tipoSanguineo,
      doencas: doador.doencas,
      altura: doador.altura,
      peso: doador.peso
    });
  }

  voltarParaPaginaAnterior(): void {
    this.router.navigate(['/cadastro/doador']);
  }

  atualizarDoador(): void {
    this.carregando.set(true);

    const objRequest: IDoador = {
      ...this.formGroup.value,
      dataNascimento: this.formatarDataRequisicao(this.dataNascimento.value),
      id: this.doador()?.id,
      agendamento: this.doador()?.agendamento,
    };

    this.doadorService
      .editarDoador(objRequest)
      .pipe(finalize(() => this.carregando.set(false)))
      .subscribe({
        next: (resultado: IDoador) => {
          this.mostrarSnackBar('Doador atualizado com sucesso!');
          this.router.navigate(['/cadastro/doador']);
        },
        error: (error: HttpErrorResponse) => this.mostrarSnackBar(error.message)
      })
  }

  private formatarDataParaDatepicker(dateString: string): Date | null {
    if (!dateString) return null;
    const [year, month, day] = dateString.split('-').map(Number);
    return new Date(year, month - 1, day);
  }

  private formatarDataRequisicao(date: Date): string {
    if (!date) return '';
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
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
