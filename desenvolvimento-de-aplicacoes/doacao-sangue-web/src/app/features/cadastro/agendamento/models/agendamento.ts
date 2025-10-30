import { IDoador } from "../../doador/models/doador";

export interface IAgendamento {
  id: number;
  dataHora: string;
  unidade: string;
  doadores: IDoador[]
}

export interface IAgendamentoCadastro {
  dataHora: string;
  unidade: string;
  doadorIds: number[]
}