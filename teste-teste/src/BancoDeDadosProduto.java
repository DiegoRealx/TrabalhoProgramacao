import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDadosProduto {
    private String caminhoArquivo;

    public BancoDeDadosProduto(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public void salvarProduto(Produto produto) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            escritor.write(produto.toString());
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvarProdutosNoArquivo(List<Produto> produtos) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Produto produto : produtos) {
                escritor.write(produto.toString());
                escritor.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> carregarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(",");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                double preco = Double.parseDouble(dados[2]);
                int quantidade = Integer.parseInt(dados[3]);
                String validade = dados[4];
                produtos.add(new Produto(id, nome, preco, quantidade, validade));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Método para obter o caminho do arquivo
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
}
