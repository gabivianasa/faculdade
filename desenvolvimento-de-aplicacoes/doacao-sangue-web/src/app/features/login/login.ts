import { Component, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

import { finalize, merge } from 'rxjs';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar';

import { LoginService } from './services/login-service';
import { ILoginResponse, IUser } from './models/user';
import { MatProgressBarModule } from '@angular/material/progress-bar';

@Component({
  selector: 'gabi-login',
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatIconModule,
    MatProgressBarModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  private readonly formBuilder = inject(FormBuilder);
  private readonly router = inject(Router);
  private readonly loginService = inject(LoginService);
  private readonly snackbar = inject(MatSnackBar);
  readonly formLogin: FormGroup;
  readonly esconderSenha = signal(true);
  readonly carregando = signal(false);

  errorMessage = signal('');

  constructor() {
    this.formLogin = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      senha: new FormControl(null, [Validators.required])
    });

    merge(this.email.statusChanges, this.email.valueChanges)
      .pipe(takeUntilDestroyed())
      .subscribe(() => this.updateErrorMessage());
  }

  get email(): FormControl {
    return this.formLogin.get('email') as FormControl;
  }

  get senha(): FormControl {
    return this.formLogin.get('senha') as FormControl;
  }

  updateErrorMessage() {
    if (this.email.hasError('required')) {
      this.errorMessage.set('Campo obrigatório');
    } else if (this.email.hasError('email')) {
      this.errorMessage.set('E-mail inválido');
    } else {
      this.errorMessage.set('');
    }
  }

  mostrarEsconderSenha(event: MouseEvent) {
    this.esconderSenha.set(!this.esconderSenha());
    event.stopPropagation();
  }

  efetuarLogin(): void {
    this.carregando.set(true);
    this.loginService
      .login(this.formLogin.value)
      .pipe(finalize(() => this.carregando.set(false)))
      .subscribe({
        next: (response: ILoginResponse) => {
          this.salvarUsuarioSessionStorage(response.token);
          this.redirecionarUsuarioParaHome();
        },
        error: (error: HttpErrorResponse) => this.mostrarSnackBar(error)
      });
  }

  private salvarUsuarioSessionStorage(token: string): void {
    const user: IUser = { email: this.email.value, token };
    sessionStorage.setItem('user', JSON.stringify(user));
  }

  private redirecionarUsuarioParaHome(): void {
    this.router.navigate(['/home']);
  }

  private mostrarSnackBar(error: HttpErrorResponse): void {
    const mensagem = error.status === 401 ? `Credenciais inválidas` : `Ocorreu um erro`;
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
