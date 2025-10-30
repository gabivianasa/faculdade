import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarAgendamento } from './editar-agendamento';

describe('EditarAgendamento', () => {
  let component: EditarAgendamento;
  let fixture: ComponentFixture<EditarAgendamento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarAgendamento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarAgendamento);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
