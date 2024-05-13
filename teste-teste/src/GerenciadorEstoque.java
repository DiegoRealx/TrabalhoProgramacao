import java.util.List;

public class GerenciadorEstoque {
    private BancoDeDadosProduto bancoDeDados;

    public GerenciadorEstoque(BancoDeDadosProduto bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }
    public void verificarEstoque() {
        List<Produto> produtos = bancoDeDados.carregarProdutos();
        
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto no estoque.");
        } else {
            System.out.println("Estoque de produtos:");
            System.out.printf("%-5s | %-20s | %-10s | %-10s | %-10s\n", "ID", "Nome", "Preço", "Quantidade", "Validade");
            System.out.println("-".repeat(65));
            for (Produto produto : produtos) {
                System.out.printf("%-5d | %-20s | R$ %-8.2f | %-10d | %-10s\n", produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidade(), produto.getValidade());
            }
        }
    }
    public String verificarValidade(int idProduto) {
        List<Produto> produtos = bancoDeDados.carregarProdutos();
        for (Produto produto : produtos) {
            if (produto.getId() == idProduto) {
                return produto.getValidade();
            }
        }
        throw new IllegalArgumentException("Produto não encontrado.");
    }
}
