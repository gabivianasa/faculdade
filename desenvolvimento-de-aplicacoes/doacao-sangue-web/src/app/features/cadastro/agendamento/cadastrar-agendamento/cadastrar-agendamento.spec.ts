import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarAgendamento } from './cadastrar-agendamento';

describe('CadastrarAgendamento', () => {
  let component: CadastrarAgendamento;
  let fixture: ComponentFixture<CadastrarAgendamento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastrarAgendamento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastrarAgendamento);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
