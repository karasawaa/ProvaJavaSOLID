- Entrega: 09/11/2025

- Documente as decisões de design no código e justifique a escolha de cada padrão para o problema específico

- O uso de princípios SOLID e de Design Patterns é exigido, sob pena de anualação da questão relativa


QUESTÃO 3 (0,25):
Contexto: Você está modelando um sistema de controle para uma usina nuclear com estados complexos de operação. A usina pode estar em: DESLIGADA, OPERACAO_NORMAL, ALERTA_AMARELO, ALERTA_VERMELHO, EMERGENCIA.

Problema:

- Cada transição de estado deve validar condições complexas (temperatura, pressão, nível de radiação)

- Algumas transições são bidirecionais, outras são unidirecionais

- Previna transições circulares perigosas

- O estado EMERGENCIA só pode ser ativado após passar por ALERTA_VERMELHO

- Adicione um modo "manutenção" que sobreescreva temporariamente os estados normais



Regras:

- OPERACAO_NORMAL → ALERTA_AMARELO: se temperatura > 300°C

- ALERTA_AMARELO → ALERTA_VERMELHO: se temperatura > 400°C por mais de 30 segundos

- ALERTA_VERMELHO → EMERGENCIA: se sistema de resfriamento falhar