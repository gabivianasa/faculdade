import { Component, signal } from '@angular/core';

import { IUser } from '../login/models/user';

@Component({
  selector: 'gabi-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home {
  readonly usuario = signal('undefined');
  
  constructor() {
    const user: IUser | null = JSON.parse(sessionStorage.getItem('user') || '{}');

    if (user) {
      this.usuario.set(user.email);
    }
  }
}
