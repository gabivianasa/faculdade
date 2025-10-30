import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioAgendamento } from './formulario-agendamento';

describe('FormularioAgendamento', () => {
  let component: FormularioAgendamento;
  let fixture: ComponentFixture<FormularioAgendamento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioAgendamento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioAgendamento);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
