import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GerenciadorProduto {
    private BancoDeDadosProduto bancoDeDados;

    public GerenciadorProduto(BancoDeDadosProduto bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    
    public void adicionarProduto(Produto produto) {
        bancoDeDados.salvarProduto(produto);
    }

    
    public void atualizarProduto(int idProduto, Produto produtoAtualizado) {
        List<Produto> produtos = bancoDeDados.carregarProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == idProduto) {
                produtos.set(i, produtoAtualizado);
                salvarProdutosNoArquivo(produtos);
                return;
            }
        }
        System.out.println("Produto com ID " + idProduto + " não encontrado.");
    }

    public void removerProduto(int idProduto) {
        List<Produto> produtos = bancoDeDados.carregarProdutos();
        produtos.removeIf(produto -> produto.getId() == idProduto);
        salvarProdutosNoArquivo(produtos);
    }

    private void salvarProdutosNoArquivo(List<Produto> produtos) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(bancoDeDados.getCaminhoArquivo()))) {
            for (Produto produto : produtos) {
                escritor.write(produto.toString());
                escritor.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
