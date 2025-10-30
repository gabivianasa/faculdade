import { Routes } from '@angular/router';

export const DOADOR_ROUTES: Routes = [
  {
    path: '',
    children: [
      { path: 'listar', loadComponent: () => import('./listar-doador/listar-doador').then(m => m.ListarDoador) },
      { path: 'cadastrar', loadComponent: () => import('./cadastrar-doador/cadastrar-doador').then(m => m.CadastrarDoador) },
      { path: 'editar/:id', loadComponent: () => import('./editar-doador/editar-doador').then(m => m.EditarDoador) },
      { path: '', redirectTo: 'listar', pathMatch: 'full' }
    ]
  }
];
