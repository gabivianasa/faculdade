import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogDadosDoador } from './dialog-dados-doador';

describe('DialogDadosDoador', () => {
  let component: DialogDadosDoador;
  let fixture: ComponentFixture<DialogDadosDoador>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DialogDadosDoador]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DialogDadosDoador);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
