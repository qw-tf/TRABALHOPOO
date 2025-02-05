//importando bibliotecas para usar listas e scanner
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ControladorDeEstoque {
    //verificador para verificar respostas
    Verificador verificador = new Verificador();
    private List<Produto> produtos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    //para adicionar um produto
    public void adicionarProduto(){

        //esse while é usado para repetir essa sessao toda vez que o usuario digitar algo invalido
        while(true){
            try{
                String nome, descricao, dataDeValidade;
                int quantidade, limiteEstoque;
                double preco;
                System.out.println("Qual o nome do produto?");
                nome = scanner.nextLine();
                verificador.verificarNome(nome);
                System.out.println("Quanto tem desse produto?");
                quantidade = scanner.nextInt();
                verificador.verificarQuantidade(quantidade);
                scanner.nextLine(); //consumir quebra de linha
                System.out.println("Qual o limite de estoque desse produto?");
                limiteEstoque = scanner.nextInt();
                verificador.verificarLimiteDeEstoque(limiteEstoque);
                System.out.println("Qual o preco desse produto?");
                preco = scanner.nextDouble();
                scanner.nextLine(); //consumir quebra de linha
                verificador.verificarPreco(preco);

                System.out.println("Quer adicionar uma descricao ao produto? s/n");
                String opcao = scanner.next();
                verificador.verificarResposta(opcao);
                if(opcao.charAt(0) == 's'){
                    System.out.println("Pode digitar a descricao do produto: ");
                    descricao = scanner.nextLine();
                    verificador.verificarNome(descricao);
                }

                //aqui ele diverge entre criar um produto perecivel ou nao
                System.out.println("O produto é perecivel ou nao? s/n");
                opcao = scanner.next();
                verificador.verificarResposta(opcao);
                if(opcao.charAt(0) == 's'){
                    System.out.println("Escreva a data de validade do produto (dd/mm/yyyy):");
                    scanner.nextLine(); //consumir quebra de linha
                    dataDeValidade = scanner.nextLine();
                    verificador.verificarDataValidade(dataDeValidade);
                    Produto prod1 = new ProdutoPerecivel(nome, quantidade, preco, dataDeValidade, limiteEstoque);
                    produtos.add(prod1);
                    Produto.aumentarProdutosTotal();
                    break;
                }
                    Produto prod1 = new Produto(nome, quantidade, preco, limiteEstoque);
                    produtos.add(prod1);
                    Produto.aumentarProdutosTotal();
                    break;
                // catchs usados para lançar a mensagem de erro e reiniciar o while
            }catch(InvalidNameException e){
                System.out.println(e.getMessage());
                continue;
            }catch(InvalidPrecoException e){
                System.out.println(e.getMessage());
                scanner.nextLine(); //consumir quebra de linha
                continue;
            }catch(InvalidQuantidadeException e){
                System.out.println(e.getMessage());
                scanner.nextLine(); //consumir quebra de linha
                continue;
            }catch(LimiteEstoqueException e){
                System.out.println(e.getMessage());
                scanner.nextLine(); //consumir quebra de linha
                continue;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                scanner.nextLine(); //consumir quebra de linha
                continue;
            }catch(ProdutoVencidoException e){
                System.out.println(e.getMessage());
                scanner.nextLine(); //consumir quebra de linha
                continue;
            }catch(InputMismatchException e){
                System.out.println("Erro, escreva o numero corretamente!");
                scanner.nextLine(); //consumir quebra de linha
                continue;
            }catch(Exception e){
                System.out.println("Erro.");
                scanner.nextLine(); //consumir quebra de linha
                continue;
            }
        }
    }

    public void excluirProduto(int codigo) throws IllegalArgumentException{
        if(codigo < 1000 || codigo > 9999){
            throw new IllegalArgumentException("Digite um codigo valido!");
        }

        Iterator<Produto> iterator = produtos.iterator();
        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            if (produto.getCodigo() == codigo) {
                iterator.remove(); // Remove o produto da lista
                System.out.println("Produto removido com sucesso!");
            }
        }
    }

}