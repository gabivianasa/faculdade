import { Component, inject } from '@angular/core';
import { Router, RouterLink } from "@angular/router";

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';


@Component({
  selector: 'gabi-header',
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    RouterLink
],
  templateUrl: './header.html',
  styleUrl: './header.scss',
})
export class Header {
  private readonly router = inject(Router);

  logout(): void {
    sessionStorage.clear();
    this.router.navigate(['login']);
  }
}
