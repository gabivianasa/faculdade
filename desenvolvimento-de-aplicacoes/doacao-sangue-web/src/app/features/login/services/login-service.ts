import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

import { Observable } from 'rxjs';

import { ILoginResponse, IUser } from '../models/user';

import { environment } from '../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private http = inject(HttpClient);
  private readonly URL_API = environment.api;
  private readonly API_PATH = 'auth/login';

  login(user: IUser): Observable<ILoginResponse> {
    return this.http
      .post<ILoginResponse>(`${this.URL_API}${this.API_PATH}`, user);
  }
}
