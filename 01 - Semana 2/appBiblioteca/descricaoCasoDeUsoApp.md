# Descrição caso de uso Aplicativo Biblioteca
## UCS 2 - Use Case 2

### Atores:
* Alunos
* Descrição: Este caso de uso permite que um Aluno reserve seu livro de interesse pelo aplicativo, e realize sua retirada posterior na biblioteca

### Condições:
* Pré-condições:
1. O Aluno deve estar logado no sistema;
2. O Aluno já deve estar com o livro selecionado.
* Pós-condições:
1. Uma reserva é criada para o Aluno e o livro;
2. A quantidade de exemplares é diminuída em 1.

### Fluxo Principal:
* 1. O Aluno seleciona a opção "Reservar";
* 2. O sistema verifica a disponibilidade;
* 3. O sistema confirma se o Aluno possui pendências;
* 4. O sistema resgista a reserva;
* 5. O sistema atualiza o status do livro;
* 6. O sistema exibe uma mensagem de sucesso com a data de retirada.

### Fluxos Alternativos:
* FA-01: Livro Indisponível:
* No passo 2 do Fluxo Principal, se não houver exemplares:
* a. O sistema informa a indisponibilidade;
* b. O sistema oferece a opção "Entrar na fila de espera";
* c. O caso de uso termina.

### Fluxo de Exceção:
* FE-01: Aluno com Pendências:
* No passo 3 do Fluxo Principal, se o tiver pendências:
* a. O sistema exibe uma mensagem de erro;
* b. O caso de uso é carregado.