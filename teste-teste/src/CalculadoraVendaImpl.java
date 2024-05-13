import java.util.List;

public class CalculadoraVendaImpl implements CalculadoraVenda {
    private BancoDeDadosProduto bancoDeDados;

    public CalculadoraVendaImpl(BancoDeDadosProduto bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    @Override
    public double calcularVenda(int idProduto, int quantidade, double percentualDesconto) {
        List<Produto> produtos = bancoDeDados.carregarProdutos();

        Produto produtoVendido = null;
        for (Produto produto : produtos) {
            if (produto.getId() == idProduto) {
                produtoVendido = produto;
                break;
            }
        }

        if (produtoVendido == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        if (produtoVendido.getQuantidade() < quantidade) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
        }

        double precoTotal = produtoVendido.getPreco() * quantidade * (1 - percentualDesconto / 100.0);

        produtoVendido.setQuantidade(produtoVendido.getQuantidade() - quantidade);
        bancoDeDados.salvarProdutosNoArquivo(produtos);

        return precoTotal;
    }
}
