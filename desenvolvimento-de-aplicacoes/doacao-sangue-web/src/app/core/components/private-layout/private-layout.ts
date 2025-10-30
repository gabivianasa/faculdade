import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { Header } from "../header/header";
import { Footer } from "../footer/footer";

@Component({
  selector: 'gabi-private-layout',
  imports: [Header, RouterOutlet, Footer],
  templateUrl: './private-layout.html'
})
export class PrivateLayout {

}
