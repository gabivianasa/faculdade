type ITipoSexo = 'F' | 'M';

interface IAgendamento {
  id: number;
  dataHora: string;
  unidade: string;
}

export interface IDoador {
	id:number;
	email: string;
	nome: string;
	dataNascimento: string;
	tipoSanguineo: string;
	doencas: string;
	altura: number;
	peso: number;
	sexo: ITipoSexo 
	agendamento: IAgendamento
}

export type IDoadorCadastro = Omit<IDoador, 'id' | 'agendamento'>;

export interface IDoadorListagem {
  id: number;
  nome: string;
}