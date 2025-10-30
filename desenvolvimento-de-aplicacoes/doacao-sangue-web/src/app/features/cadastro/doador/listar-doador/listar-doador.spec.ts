import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarDoador } from './listar-doador';

describe('ListarDoador', () => {
  let component: ListarDoador;
  let fixture: ComponentFixture<ListarDoador>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarDoador]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarDoador);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
