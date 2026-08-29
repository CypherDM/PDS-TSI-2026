# Especificação de Caso de Uso
 
## UC-01 — Gerador de Rotina com IA
 
### 1. Visão Geral
 
**ID do Caso de Uso:** UC-01
**Nome:** NextIA
**Atores:** Usuário, Inteligência Artificial (IA)
 
**Descrição:**
 
Este caso de uso permite que o usuário informe suas atividades, compromissos, horários disponíveis e prioridades para que o sistema, utilizando inteligência artificial, gere uma rotina personalizada.
 
O objetivo é auxiliar o usuário na organização de suas atividades, distribuindo as tarefas de acordo com os horários disponíveis e as prioridades informadas. Ao final, o usuário poderá visualizar a rotina gerada e utilizá-la para organizar seu dia.
 
---
 
## 2. Condições
 
### Pré-condições
 
O que obrigatoriamente precisa ser verdade para que este caso de uso possa começar:
 
1. O usuário deve estar autenticado no sistema.
2. O sistema deve estar disponível.
3. O sistema deve possuir acesso ao serviço de inteligência artificial.
4. O usuário deve fornecer as informações necessárias para a geração da rotina.
### Pós-condições
 
Após a conclusão do caso de uso:
 
1. Uma rotina personalizada é gerada pela IA.
2. A rotina é apresentada ao usuário.
3. A rotina gerada pode ser armazenada no sistema.
4. O usuário pode consultar posteriormente a rotina criada.
---
 
## 3. Fluxos de Eventos
 
### Fluxo Principal — Caminho Feliz
 
| Passo | Ação                                                                                                                                      |
| ----- | ----------------------------------------------------------------------------------------------------------------------------------------- |
| 1     | O Usuário acessa a funcionalidade de geração de rotina.                                                                                   |
| 2     | O Sistema apresenta o formulário para informar as atividades, compromissos, horários disponíveis e prioridades.                           |
| 3     | O Usuário informa suas atividades e preferências.                                                                                         |
| 4     | O Usuário solicita a geração da rotina.                                                                                                   |
| 5     | O Sistema valida as informações fornecidas pelo Usuário.                                                                                  |
| 6     | O Sistema envia as informações para o serviço de Inteligência Artificial.                                                                 |
| 7     | A Inteligência Artificial analisa as informações recebidas e organiza as atividades de acordo com as preferências e horários disponíveis. |
| 8     | A Inteligência Artificial retorna uma sugestão de rotina para o Sistema.                                                                  |
| 9     | O Sistema valida a consistência da rotina retornada (ex.: ausência de conflitos de horário).                                             |
| 10    | O Sistema apresenta a rotina gerada ao Usuário.                                                                                           |
| 11    | O Usuário visualiza a rotina e confirma a utilização da sugestão.                                                                         |
| 12    | O Sistema salva a rotina no cadastro do Usuário.                                                                                          |
| 13    | O Sistema informa ao Usuário que a rotina foi criada com sucesso.                                                                         |
 
---
 
### Fluxos Alternativos
 
#### FA-01 — Usuário deseja alterar a rotina
 
No passo 10 do Fluxo Principal, caso o Usuário não esteja satisfeito com a rotina gerada:
 
1. O Usuário solicita alterações na rotina.
2. O Sistema apresenta as opções disponíveis para alteração.
3. O Usuário informa quais alterações deseja realizar.
4. O Sistema envia as novas informações para a Inteligência Artificial.
5. A Inteligência Artificial gera uma nova sugestão de rotina.
6. O Sistema retorna ao passo 9 do Fluxo Principal (validação de consistência).
---
 
#### FA-02 — Usuário não informa todas as atividades
 
No passo 5 do Fluxo Principal, caso o Usuário não informe alguma informação obrigatória:
 
1. O Sistema identifica que existem informações necessárias não preenchidas.
2. O Sistema informa ao Usuário quais informações precisam ser preenchidas.
3. O Usuário fornece as informações solicitadas.
4. O Sistema continua o Fluxo Principal a partir da validação das informações.
---
 
#### FA-03 — Usuário solicita uma nova rotina
 
Após a geração da rotina:
 
1. O Usuário seleciona a opção para gerar uma nova rotina.
2. O Sistema solicita as informações necessárias.
3. O Usuário informa ou altera suas atividades e preferências.
4. O Sistema solicita uma nova sugestão à Inteligência Artificial.
5. A IA gera uma nova rotina.
6. O Sistema retorna ao passo 9 do Fluxo Principal (validação de consistência).
---
 
## Fluxos de Exceção
 
### FE-01 — Serviço de IA indisponível ou com falha de comunicação
 
No passo 6 ou 7 do Fluxo Principal, caso o Sistema não consiga se conectar ao serviço de Inteligência Artificial ou a comunicação seja interrompida (timeout, erro de rede, erro no serviço):
 
1. O Sistema identifica a falha de conexão ou comunicação com o serviço de IA.
2. O Sistema informa ao Usuário que não foi possível gerar a rotina naquele momento.
3. O Sistema oferece a opção de tentar novamente, respeitando o limite de 3 novas tentativas automáticas (RN-03).
4. Caso o Usuário opte por tentar novamente, o Sistema reinicia o Fluxo Principal a partir do passo 6.
5. Caso as tentativas se esgotem ou o Usuário não tente novamente, o caso de uso é encerrado.
---
 
### FE-02 — Erro ao salvar a rotina
 
No passo 12 do Fluxo Principal, caso ocorra um erro ao salvar a rotina:
 
1. O Sistema identifica que não foi possível armazenar a rotina.
2. O Sistema informa ao Usuário que ocorreu um erro ao salvar a rotina.
3. O Sistema mantém a rotina apresentada para que o Usuário possa tentar salvá-la novamente.
4. O caso de uso permanece disponível para uma nova tentativa.
---
 
### FE-03 — Rotina gerada com conflito de horários
 
No passo 9 do Fluxo Principal, caso o Sistema identifique que a rotina retornada pela IA contém atividades sobrepostas ou incompatíveis com os compromissos fixos informados:
 
1. O Sistema rejeita a rotina retornada.
2. O Sistema solicita automaticamente uma nova geração à Inteligência Artificial, respeitando o limite de 3 tentativas (RN-03).
3. Caso uma rotina consistente seja obtida, o Fluxo Principal continua normalmente a partir do passo 10.
4. Caso o limite de tentativas seja atingido sem sucesso, o Sistema informa ao Usuário que não foi possível gerar uma rotina sem conflitos e sugere o ajuste manual das informações fornecidas (retorno ao passo 3).
---
 
## 4. Regras de Negócio
 
* **RN-01**: A rotina gerada não pode conter sobreposição de horários entre atividades.
* **RN-02**: O Sistema deve respeitar os compromissos fixos informados pelo Usuário como inegociáveis na geração da rotina.
* **RN-03**: Tentativas automáticas de nova geração, seja por falha de comunicação (FE-01) ou por inconsistência (FE-03), são limitadas a 3 tentativas.
* **RN-04**: Toda rotina salva deve conter data e hora de criação, para fins de histórico.
---
 
## 5. Requisitos Especiais
 
* A geração da rotina deve ocorrer em, no máximo, 10 segundos, de modo a proporcionar uma boa experiência ao usuário.
* As informações fornecidas pelo Usuário devem ser armazenadas de forma segura, com controle de acesso restrito ao próprio usuário.
* Os dados pessoais e as informações utilizadas para gerar a rotina devem seguir princípios de proteção de dados (ex.: LGPD).
* As comunicações entre o Sistema e o serviço de Inteligência Artificial devem ser realizadas via canal criptografado (HTTPS/TLS).
* As rotinas geradas devem ficar disponíveis para consulta posterior, organizadas por data de criação.
* O Sistema deve informar claramente ao Usuário quando uma rotina for gerada ou quando ocorrer algum problema durante o processo.
* As informações utilizadas pela IA devem ser estruturadas (ex.: JSON) de forma que possibilitem a geração de uma rotina coerente com os horários e prioridades informados pelo Usuário.