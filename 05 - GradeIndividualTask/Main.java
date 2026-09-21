import java.util.ArrayList;
import java.time.LocalDateTime;

class Cliente {
    private String nome;
    public Cliente(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void escolherProduto(int qtd) {
        System.out.println(nome + " escolheu " + qtd + " produto(s).");
    }
}

class Produto {
    private String codigo;
    private String nome;
    private double precoUnitario;
    private int quantidadeEstoque;
    public Produto(String codigo, String nome, double precoUnitario, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    public boolean temEstoqueSuficiente(int qtd) {
        return qtd <= quantidadeEstoque;
    }
    public void baixarEstoque(int qtd) {
        quantidadeEstoque = quantidadeEstoque - qtd;
    }
    public void adicionarEstoque(int qtd) {
        quantidadeEstoque = quantidadeEstoque + qtd;
    }
    public String toString() {
        return codigo + " - " + nome
                + " - R$ " + precoUnitario
                + " - Estoque: " + quantidadeEstoque;
    }
}

class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double subtotal;
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = calcularSubtotal();
    }
    public Produto getProduto() {
        return produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.subtotal = calcularSubtotal();
    }
    public double calcularSubtotal() {
        return produto.getPrecoUnitario() * quantidade;
    }
}

class Pagamento {
    private String forma;
    private double valorPago;
    public Pagamento(String forma, double valorPago) {
        this.forma = forma;
        this.valorPago = valorPago;
    }
    public String getForma() {
        return forma;
    }
    public double getValorPago() {
        return valorPago;
    }
    public boolean ehSuficiente(double total) {
        return valorPago >= total;
    }
    public double calcularTroco(double total) {
        return valorPago - total;
    }
}

class Comprovante {
    private int numero;
    private LocalDateTime dataHora;
    public Comprovante(int numero) {
        this.numero = numero;
        this.dataHora = LocalDateTime.now();
    }
    public int getNumero() {
        return numero;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public String gerarTexto() {
        return "Comprovante do pedido " + numero
                + " - Data: " + dataHora;
    }
}

class Pedido {
    private int numero;
    private LocalDateTime dataHora;
    private String status;
    private ArrayList<ItemPedido> itens;
    private Pagamento pagamento;
    private Comprovante comprovante;
    public Pedido(int numero) {
        this.numero = numero;
        this.dataHora = LocalDateTime.now();
        this.status = "ABERTO";
        this.itens = new ArrayList<ItemPedido>();
    }
    public int getNumero() {
        return numero;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public String getStatus() {
        return status;
    }
    public ArrayList<ItemPedido> getItens() {
        return itens;
    }
    public Pagamento getPagamento() {
        return pagamento;
    }
    public Comprovante getComprovante() {
        return comprovante;
    }
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    public void adicionarItem(Produto produto, int quantidade) {
        ItemPedido item = new ItemPedido(produto, quantidade);
        itens.add(item);
    }
    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < itens.size(); i++) {
            total = total + itens.get(i).calcularSubtotal();
        }
        return total;
    }

    public boolean podeFinalizar() {
        if (itens.size() == 0) {
            return false;
        }
        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = itens.get(i);
            if (!item.getProduto().temEstoqueSuficiente(item.getQuantidade())) {
                return false;
            }
        }
        if (pagamento == null) {
            return false;
        }
        if (!pagamento.ehSuficiente(calcularTotal())) {
            return false;
        }
        return true;
    }
    public void finalizar() {
        if (!podeFinalizar()) {
            return;
        }
        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = itens.get(i);
            item.getProduto().baixarEstoque(item.getQuantidade());
        }
        status = "FINALIZADO";
        comprovante = new Comprovante(numero);
    }
    public void cancelar() {
        status = "CANCELADO";
    }
}

class Atendente {
    private String nome;
    public Atendente(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public Pedido registrarPedido() {
        return new Pedido(1);
    }
    public Pagamento receberPagamento(double valor) {
        return new Pagamento("Dinheiro", valor);
    }
    public Comprovante emitirComprovante(Pedido pedido) {
        return pedido.getComprovante();
    }
}

class Gerente extends Atendente {
    private ArrayList<Produto> produtos;
    public Gerente(String nome) {
        super(nome);
        produtos = new ArrayList<Produto>();
    }
    public void cadastrarProduto(Produto p) {
        produtos.add(p);
    }
    public void alterarPreco(String codigo, double novoPreco) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getCodigo().equals(codigo)) {
                produto.setPrecoUnitario(novoPreco);
            }
        }
    }
    public void atualizarEstoque(int qtd) {
        if (produtos.size() > 0) {
            produtos.get(0).adicionarEstoque(qtd);
        }
    }
    public void removerProduto(String codigo) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo().equals(codigo)) {
                produtos.remove(i);
                break;
            }
        }
    }
    public ArrayList<Produto> consultarVendas() {
        return produtos;
    }
}

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("João");

        Produto coxinha = new Produto(
                "P001",
                "Coxinha",
                6.50,
                10
        );
        Produto suco = new Produto(
                "P002",
                "Suco de laranja",
                5.00,
                15
        );
        Produto brigadeiro = new Produto(
                "P003",
                "Brigadeiro",
                3.00,
                20
        );
        System.out.println("=== PRODUTOS ===");
        System.out.println(coxinha);
        System.out.println(suco);
        System.out.println(brigadeiro);

        Atendente atendente = new Atendente("Carlos");

        Pedido pedido = new Pedido(1);
        pedido.adicionarItem(coxinha, 2);
        pedido.adicionarItem(suco, 1);

        System.out.println();
        System.out.println("=== PEDIDO ===");
        for (int i = 0; i < pedido.getItens().size(); i++) {
            ItemPedido item = pedido.getItens().get(i);
            System.out.println(
                    item.getQuantidade()
                    + "x "
                    + item.getProduto().getNome()
                    + " = R$ "
                    + item.calcularSubtotal()
            );
        }
        System.out.println("Total: R$ " + pedido.calcularTotal());

        Pagamento pagamento = atendente.receberPagamento(20.00);
        pedido.setPagamento(pagamento);

        if (pedido.podeFinalizar()) {
            pedido.finalizar();
            System.out.println();
            System.out.println("=== VENDA FINALIZADA ===");
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Atendente: " + atendente.getNome());
            System.out.println("Pedido: " + pedido.getNumero());
            System.out.println("Status: " + pedido.getStatus());
            System.out.println("Pagamento: " + pagamento.getForma());
            System.out.println("Valor pago: R$ " + pagamento.getValorPago());
            System.out.println(
                    "Troco: R$ "
                    + pagamento.calcularTroco(pedido.calcularTotal())
            );
            System.out.println();
            System.out.println("=== COMPROVANTE ===");
            System.out.println(pedido.getComprovante().gerarTexto());
            System.out.println();
            System.out.println("=== ESTOQUE ===");
            System.out.println(coxinha);
            System.out.println(suco);
        } else {
            System.out.println();
            System.out.println("Não foi possível finalizar o pedido.");
        }

        Gerente gerente = new Gerente("Maria");
        gerente.cadastrarProduto(brigadeiro);
        gerente.alterarPreco("P003", 3.50);

        System.out.println();
        System.out.println("=== PRODUTO ALTERADO PELO GERENTE ===");
        System.out.println(brigadeiro);

        Pedido pedido2 = new Pedido(2);
        pedido2.adicionarItem(brigadeiro, 100);
        Pagamento pagamento2 = new Pagamento("PIX", 300.00);
        pedido2.setPagamento(pagamento2);

        if (pedido2.podeFinalizar()) {
            pedido2.finalizar();
            System.out.println("Pedido 2 finalizado com sucesso.");
        } else {
            System.out.println();
            System.out.println("Pedido 2 não pode ser finalizado.");
            System.out.println(
                    "Estoque insuficiente para "
                    + brigadeiro.getNome()
            );
        }
    }
}