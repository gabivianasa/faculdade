import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarAgendamento } from './listar-agendamento';

describe('ListarAgendamento', () => {
  let component: ListarAgendamento;
  let fixture: ComponentFixture<ListarAgendamento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarAgendamento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarAgendamento);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
