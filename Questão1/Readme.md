
- Entrega: 09/11/2025

- Documente as decisões de design no código e justifique a escolha de cada padrão para o problema específico

- O uso de princípios SOLID e de Design Patterns é exigido, sob pena de anualação da questão relativa


QUESTÃO 1 (Nota: 0,5): 
Contexto: Você está desenvolvendo um sistema de processamento de dados para uma empresa financeira que lida com diferentes tipos de análise de risco. O sistema precisa calcular métricas de risco usando diferentes algoritmos (Value at Risk, Expected Shortfall, Stress Testing) que podem mudar dinamicamente durante a execução.

Problema:

- Cada algoritmo de risco deve ser intercambiável em tempo de execução

- Os algoritmos devem ser capazes de compartilhar um contexto complexo com múltiplos parâmetros financeiros

- Deve ser possível trocar de algoritmo de acordo com a necessidade de negócios



Restrições:

- O cliente deve poder mudar de algoritmo sem conhecer os detalhes de implementação

- Implemente pelo menos 3 algoritmos diferentes com cálculos dummy (podem ser mensagens de texto) distintos