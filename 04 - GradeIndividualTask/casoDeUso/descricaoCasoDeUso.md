## 1. Atores do sistema

- **Cliente** – consulta o cardápio, monta o pedido e paga.
- **Atendente** – registra o pedido, finaliza a venda, recebe o pagamento e emite o comprovante.
- **Gerente** – cadastra produtos, altera preços, atualiza estoque e consulta vendas (também pode agir como atendente).

## 2. Casos de uso principais

- Consultar cardápio
- Registrar pedido
- Calcular total do pedido
- Finalizar pedido
- Registrar pagamento
- Calcular troco
- Emitir comprovante
- Cancelar pedido
- Cadastrar produto
- Alterar preço
- Atualizar estoque
- Remover produto do cardápio
- Consultar vendas realizadas

## 3. Descrição textual dos casos de uso

### UC – Finalizar pedido e registrar pagamento

- **Objetivo:** concluir a venda, calcular o total, receber o pagamento e emitir o comprovante.
- **Atores:** Cliente, Atendente.
- **Fluxo principal:**
  1. O atendente fecha o pedido.
  2. O sistema verifica se há pelo menos um item e estoque suficiente para cada um.
  3. O sistema calcula o total somando os subtotais.
  4. O cliente informa a forma de pagamento e o valor.
  5. O sistema verifica se o valor cobre o total.
  6. O sistema dá baixa no estoque e registra a venda.
  7. O sistema emite o comprovante.
- **Fluxo alternativo:** se a forma for dinheiro e o valor pago for maior que o total, o sistema calcula o troco antes de emitir o comprovante.
- **Exceção:** se o valor pago for insuficiente, o sistema não finaliza o pedido e solicita complemento ou nova forma de pagamento.

### UC – Atualizar estoque

- **Objetivo:** ajustar a quantidade em estoque de um produto por entrada ou saída.
- **Atores:** Gerente.
- **Fluxo principal:**
  1. O gerente busca o produto.
  2. O gerente escolhe entrada ou saída e informa a quantidade.
  3. O sistema valida a quantidade.
  4. O sistema atualiza o estoque e confirma a operação.
- **Exceção:** se a saída solicitada for maior que o estoque disponível, o sistema rejeita a operação e mantém o estoque inalterado.