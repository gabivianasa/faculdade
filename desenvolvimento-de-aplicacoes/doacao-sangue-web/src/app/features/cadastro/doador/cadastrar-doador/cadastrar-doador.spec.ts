import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarDoador } from './cadastrar-doador';

describe('CadastrarDoador', () => {
  let component: CadastrarDoador;
  let fixture: ComponentFixture<CadastrarDoador>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastrarDoador]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastrarDoador);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
