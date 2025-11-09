
- Entrega: 09/11/2025

- Documente as decisões de design no código e justifique a escolha de cada padrão para o problema específico

- O uso de princípios SOLID e de Design Patterns é exigido, sob pena de anualação da questão relativa


QUESTÃO 4 (0,25):
Contexto: Desenvolva um sistema de validação de documentos fiscais eletrônicos (NF-e) que precisa aplicar múltiplas regras de validação em cadeia.

Problema:

- Cada validador especializado verifica um aspecto específico do documento

- A cadeia deve suportar validações condicionais (se validador X falhar, pule Y)

- Implemente um mecanismo de "circuit breaker" que interrompa a cadeia após 3 validações falharem

- Adicione capacidade de rollback para validadores que modificam o documento



Validadores Requeridos:

1. Validador de Schema XML contra XSD

2. Validador de Certificado Digital (expiração e revogação)

3. Validador de Regras Fiscais (cálculo de impostos)

4. Validador de Banco de dados (duplicidade de número)

5. Validador de Serviço SEFAZ (consulta online)



Restrições:

- Os validadores 3 e 5 devem ser executados apenas se os anteriores passarem

- O validador 4 deve fazer rollback da inserção se validações subsequentes falharem

- Implemente timeout individual para cada validador