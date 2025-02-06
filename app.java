import java.util.Scanner;

@SuppressWarnings("unused")
public class app {
    public static void main(String[] args){
        ControladorDeEstoque controlador = new ControladorDeEstoque();
        AtualizarEstoque atualizador = new AtualizarEstoque(controlador);
        ManipularArquivo manipulador = new ManipularArquivo("produtos.csv", controlador);
        Scanner scanner = new Scanner(System.in);
        manipulador.carregarProdutos();

        while(true){
            System.out.println("Menu:\n1-adicionar produtos\n2-excluir produto\n3-salvar e sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch(opcao){
                case 1:
                controlador.adicionarProduto();
                manipulador.salvarArquivo();
                break;

                case 2:
                controlador.excluirProduto();
                manipulador.salvarArquivo();
                break;

                case 3:
                scanner.close();
                System.exit(0);

                default:
                System.out.println("Opçao invalida! tente novamente");
            }
        }
    }
}
