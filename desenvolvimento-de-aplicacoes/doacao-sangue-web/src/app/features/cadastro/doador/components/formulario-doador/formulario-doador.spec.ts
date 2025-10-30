import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioDoador } from './formulario-doador';

describe('FormularioDoador', () => {
  let component: FormularioDoador;
  let fixture: ComponentFixture<FormularioDoador>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioDoador]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioDoador);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
