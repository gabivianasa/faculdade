import { Routes } from '@angular/router';

import { loginGuard } from './core/guards/login-guard';
import { authGuard } from './core/guards/auth-guard';
import { PrivateLayout } from './core/components/private-layout/private-layout';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./features/login/login').then(m => m.Login),
    canActivate: [loginGuard]
  },
  {
    path: '',
    component: PrivateLayout,
    canActivate: [authGuard],
    children: [
      { path: 'home', loadComponent: () => import('./features/home/home').then(m => m.Home) },
      { path: 'cadastro', loadChildren: () => import('./features/cadastro/cadastro.routes').then(m => m.CADASTRO_ROUTES) }
    ]
  },
  // {
  //   path: 'home',
  //   loadComponent: () => import('./features/home/home').then(m => m.Home),
  //   canActivate: [authGuard]
  // },
  // {
  //   path: 'cadastro',
  //   loadChildren: () => import('./features/cadastro/cadastro.routes').then(m => m.CADASTRO_ROUTES),
  //   canActivate: [authGuard]
  // },
  // { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', loadComponent: () => import('./features/pagina-nao-encontrada/pagina-nao-encontrada').then(m => m.PaginaNaoEncontrada) }
];
