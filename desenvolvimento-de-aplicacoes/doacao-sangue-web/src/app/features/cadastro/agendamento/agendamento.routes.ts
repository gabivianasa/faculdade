import { Routes } from '@angular/router';

export const AGENDAMENTO_ROUTES: Routes = [
  {
    path: '',
    children: [
      { path: 'listar', loadComponent: () => import('./listar-agendamento/listar-agendamento').then(m => m.ListarAgendamento) },
      { path: 'cadastrar', loadComponent: () => import('./cadastrar-agendamento/cadastrar-agendamento').then(m => m.CadastrarAgendamento) },
      { path: 'editar/:id', loadComponent: () => import('./editar-agendamento/editar-agendamento').then(m => m.EditarAgendamento) },
      { path: '', redirectTo: 'listar', pathMatch: 'full' }
    ]
  }
];
