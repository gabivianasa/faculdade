import { Component, signal } from '@angular/core';

import { MatIcon } from "@angular/material/icon";

@Component({
  selector: 'gabi-footer',
  imports: [MatIcon],
  templateUrl: './footer.html',
  styleUrl: './footer.scss',
})
export class Footer {
  readonly anoAtual = signal(new Date().getFullYear());
}
