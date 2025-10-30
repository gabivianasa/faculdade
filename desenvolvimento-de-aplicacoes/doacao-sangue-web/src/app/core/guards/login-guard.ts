import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const loginGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const userString = sessionStorage.getItem('user');
  const user = userString ? JSON.parse(userString) : null;

  if (user && user.token) {
    return router.parseUrl('/home');
  }

  return true;
}
