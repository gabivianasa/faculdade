import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAgendamento } from './dialog-agendamento';

describe('DialogAgendamento', () => {
  let component: DialogAgendamento;
  let fixture: ComponentFixture<DialogAgendamento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DialogAgendamento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DialogAgendamento);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
