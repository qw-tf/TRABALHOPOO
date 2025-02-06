//importando bibliotecas para usar listas e scanner
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ControladorDeEstoque {
    //verificador para verificar respostas
    Verificador verificador = new Verificador();
    //para conseguir salvar os dados

    //cria uma instancia de controlador para dar ao construtor do manipulador de arquivo
    private ControladorDeEstoque controlador;
    ManipularArquivo arquivo = new ManipularArquivo("produtos.csv", controlador);
    //cria uma nova lista para guardar os produtos e salvar corretamente
    private List<Produto> produtos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    //get da lista ja que ela é privada
    public List<Produto> getProdutos() {
        return produtos;
    }

    //para adicionar um produto
    public Produto adicionarProduto(){

        //esse while é usado para repetir essa sessao toda vez que o usuario digitar algo invalido
        while(true){
            try{
                String nome, descricao = null, dataDeValidade;
                int quantidade, limiteEstoque;
                double preco;
                boolean descricaoAdd; //booleano para checar se a descricao foi adicionada
                descricaoAdd = false; //setta ela como falsa a cada repeticao para funcionar corretamente
                System.out.println("Qual o nome do produto?");
                nome = scanner.nextLine();
                verificador.verificarNome(nome); //chama classe verificador para validar resposta e entra nos catchs se for invalida
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
                    scanner.nextLine(); //consumir quebra de linha
                    descricao = scanner.nextLine();
                    descricaoAdd = true;
                    verificador.verificarNome(descricao);
                }

                //aqui ele diverge entre criar um produto perecivel ou nao
                System.out.println("O produto é perecivel ou nao? s/n");
                opcao = scanner.next();
                verificador.verificarResposta(opcao); //valida se a resposta é "s" ou "n"
                scanner.nextLine(); //consumir quebra de linha
                if(opcao.charAt(0) == 's'){ 
                    System.out.println("Escreva a data de validade do produto (dd/mm/yyyy):");
                    dataDeValidade = scanner.nextLine();
                    verificador.verificarDataValidade(dataDeValidade);
                    // cria a instancia do produto perecivel
                    Produto prod1 = new ProdutoPerecivel(nome, quantidade, preco, dataDeValidade, limiteEstoque); 
                    if(descricaoAdd){ //adiciona a descricao previamente colocada pelo usuario somente se "descricaoAdd" for verdadeira
                        prod1.setDescricao(descricao);
                    }
                    Produto.aumentarProdutosTotal(); // aumenta o total de produtos para atualizar coisas como o codigo do produto
                    return prod1;
                }
                    Produto prod1 = new Produto(nome, quantidade, preco, limiteEstoque); // cria a instancia do produto comum
                    if(descricaoAdd){
                        prod1.setDescricao(descricao);
                    }
                    
                    Produto.aumentarProdutosTotal();
                    return prod1;
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
        if(codigo < 1000 && codigo > 9999){ // checa se o codigo é valido (possue 4 digitos)
            throw new IllegalArgumentException("Digite um codigo valido!");
        }

        Iterator<Produto> iterator = produtos.iterator(); //iterator para percorrer pela lista e remover os itens corretamente
        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            if (produto.getCodigo() == codigo) {
                iterator.remove(); // Remove o produto da lista
                System.out.println("Produto removido com sucesso!");
            }
        }
        System.out.println("Produto com esse codigo nao existe!");
    }

    public void aumentarLista(Produto produto){
        produtos.add(produto); //metodo para acessar a lista privada
    }
}