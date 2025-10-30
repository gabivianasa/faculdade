import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarDoador } from './editar-doador';

describe('EditarDoador', () => {
  let component: EditarDoador;
  let fixture: ComponentFixture<EditarDoador>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarDoador]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarDoador);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
