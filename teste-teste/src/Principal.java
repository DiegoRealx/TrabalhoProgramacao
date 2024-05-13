import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        BancoDeDadosProduto bancoDeDados = new BancoDeDadosProduto("produtos.txt");
        GerenciadorProduto gerenciadorProduto = new GerenciadorProduto(bancoDeDados);
        CalculadoraVenda calculadoraVenda = new CalculadoraVendaImpl(bancoDeDados);
        GerenciadorEstoque gerenciadorEstoque = new GerenciadorEstoque(bancoDeDados);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Atualizar produto");
            System.out.println("3. Remover produto");
            System.out.println("4. Calcular venda com desconto");
            System.out.println("5. Calcular venda sem desconto");
            System.out.println("6. Verificar estoque");
            System.out.println("7. Verificar validade de um produto");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    System.out.print("ID do produto: ");
                    int idProduto = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço do produto: ");
                    double preco = Double.parseDouble(scanner.nextLine());
                    System.out.print("Quantidade do produto: ");
                    int quantidade = Integer.parseInt(scanner.nextLine());
                    System.out.print("Validade do produto: ");
                    String validade = scanner.nextLine();
                    Produto produto = new Produto(idProduto, nome, preco, quantidade, validade);
                    gerenciadorProduto.adicionarProduto(produto);
                    System.out.println("Produto cadastrado com sucesso.");
                    break;

                case "2":
                    System.out.print("ID do produto a ser atualizado: ");
                    int idAtualizar = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo nome do produto: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo preço do produto: ");
                    double novoPreco = Double.parseDouble(scanner.nextLine());
                    System.out.print("Nova quantidade do produto: ");
                    int novaQuantidade = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nova validade do produto: ");
                    String novaValidade = scanner.nextLine();
                    Produto produtoAtualizado = new Produto(idAtualizar, novoNome, novoPreco, novaQuantidade, novaValidade);
                    gerenciadorProduto.atualizarProduto(idAtualizar, produtoAtualizado);
                    System.out.println("Produto atualizado com sucesso.");
                    break;

                case "3":
                    System.out.print("ID do produto a ser removido: ");
                    int idRemover = Integer.parseInt(scanner.nextLine());
                    gerenciadorProduto.removerProduto(idRemover);
                    System.out.println("Produto removido com sucesso.");
                    break;

                case "4":
                    System.out.print("ID do produto: ");
                    int idCalcular = Integer.parseInt(scanner.nextLine());
                    System.out.print("Quantidade a ser comprada: ");
                    int quantidadeCalcular = Integer.parseInt(scanner.nextLine());
                    System.out.print("Desconto percentual: ");
                    double desconto = Double.parseDouble(scanner.nextLine());
                    try {
                        double precoFinal = calculadoraVenda.calcularVenda(idCalcular, quantidadeCalcular, desconto);
                        System.out.println("O preço final da venda é: R$" + precoFinal);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "5":
                    System.out.print("ID do produto: ");
                    int idSemDesconto = Integer.parseInt(scanner.nextLine());
                    System.out.print("Quantidade a ser comprada: ");
                    int quantidadeSemDesconto = Integer.parseInt(scanner.nextLine());
                    try {
                        double precoFinalSemDesconto = calculadoraVenda.calcularVenda(idSemDesconto, quantidadeSemDesconto, 0);
                        System.out.println("O preço final da venda sem desconto é: R$" + precoFinalSemDesconto);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "6":
                    gerenciadorEstoque.verificarEstoque();
                    break;

                case "7":
                    System.out.print("ID do produto: ");
                    int idVerificar = Integer.parseInt(scanner.nextLine());
                    try {
                        String validadeProduto = gerenciadorEstoque.verificarValidade(idVerificar);
                        System.out.println("A validade do produto é: " + validadeProduto);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "0":
                    System.out.println("Encerrando o sistema.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
