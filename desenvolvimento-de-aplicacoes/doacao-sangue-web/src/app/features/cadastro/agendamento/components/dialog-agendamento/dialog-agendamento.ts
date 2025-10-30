import { Component, inject } from '@angular/core';

import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';

import { IDoador } from '../../../doador/models/doador';

@Component({
  selector: 'gabi-dialog-agendamento',
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatListModule,
    MatIconModule,
    MatDividerModule,
  ],
  templateUrl: './dialog-agendamento.html',
  styleUrl: './dialog-agendamento.scss',
})
export class DialogAgendamento {
  readonly data: IDoador[] = inject(MAT_DIALOG_DATA);

}
