import { Component, Input, signal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';

@Component({
  selector: 'gabi-formulario-doador',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatDatepickerModule
  ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './formulario-doador.html',
  styleUrl: './formulario-doador.scss',
})
export class FormularioDoador {
  @Input() formGroup!: FormGroup;
  readonly tiposSanguineos = signal(["A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"]);
  readonly dataMaxima = signal(new Date());

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
}
