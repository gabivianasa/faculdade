import { Routes } from '@angular/router';

import { Cadastro } from './cadastro';

export const CADASTRO_ROUTES: Routes = [
  {
    path: '',
    component: Cadastro,
    children: [
      {
        path: 'doador',
        loadChildren: () => import('./doador/doador.routes').then(r => r.DOADOR_ROUTES)
      },
      {
        path: 'agendamento',
        loadChildren: () => import('./agendamento/agendamento.routes').then(r => r.AGENDAMENTO_ROUTES)
      },
      { path: '', redirectTo: 'doador', pathMatch: 'full' }
    ]
  },
];
