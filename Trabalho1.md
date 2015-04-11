# Implementando um agente reflexo em Java. #

Entrega: por email para o monitor até a data de entrega.

Trabalho em grupo: Grupos de 2 ou 3 alunos (nem mais, nem menos).

Avaliação: Entrega obrigatória. A entrega de boas implementações para as três tarefas descritas abaixo vale 1,0 ponto na P1.
Data de entrega: 09/09/2011
Monitor: Pedro Dalcin, email: pedro (at) dalcin.org
Material de apoio: Projeto AIMA, que oferece implementações em Java dos algoritmos do livro "Artificial Intelligenge: A modern approach", de Russell e Norvig.

Tarefa: Utilizar as classes disponibilizadas no projeto AIMA para implementar o agente reflexo do aspirador de pó e construir:
  * Um agente aspirador de pó que atue num ambiente com 4 salas A,B,C e D, com a seguinte configuração:
|A|B|
|:|:|
|C |D |

  * Considerando o ambiente das 4 salas, um agente aspirador de pó cujo sensor de sujeira está quebrado. Independente do estado de sujeira da sala, o sensor sempre retorna a percepção "`*`" devido a um mau funcionamento.
  * Um sistema multi-agente com dois agentes: o agente "lava o chão" e o agente "seca o chão", que fazem respectivamente os trabalhos de lavar o chão, quando o chão está sujo, e de secar o chão, quando o chão está molhado. Considere que os agentes atuam no mundo com 4 salas.