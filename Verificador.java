import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//CLASSE CRIADA PARA FACILITAR E PREVENIR MUITA REPETICAO DE CODIGO DESNESCESSARIA!!!!
public class Verificador {
        int quantidade;
        public void verificarDataValidade(String dataDeValidade) throws ProdutoVencidoException{
        //CONSTANTE PARA DECLARAR FORMATACAO!!
        DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //checando se a data esta correta
        try{
            LocalDate dataValida = LocalDate.parse(dataDeValidade, FORMATO);
            if(dataValida.isBefore(LocalDate.now())){              
                throw new ProdutoVencidoException("Produto ja esta vencido!"); 
            }
        }catch(DateTimeParseException e){
            System.out.println("Data invalida!");
        }
    }


    //classes para checagem autoexplicativas
    public void verificarNome(String nome) throws InvalidNameException{
            if(nome.isBlank()){
                throw new InvalidNameException("Nao pode estar vazio!");
            }
    }

    public void verificarPreco(double preco) throws InvalidPrecoException{
            if(preco < 0){
                throw new InvalidPrecoException("Preco nao pode ser negativo!");
            }
    }

    public void verificarQuantidade(int quantidade) throws InvalidQuantidadeException{
            if(quantidade < 0){
                throw new InvalidQuantidadeException("Quantidade nao pode ser negativa!");
            }
            this.quantidade = quantidade;
    }

    public void verificarLimiteDeEstoque(int limiteEstoque) throws LimiteEstoqueException{
            if(limiteEstoque < 0 || limiteEstoque < quantidade){
                throw new LimiteEstoqueException("Limite de estoque invalido!");
            }
    }

    public void verificarResposta(String opcao) throws IllegalArgumentException{
            if (opcao.length() != 1) {;
                throw new IllegalArgumentException("Digite apenas um caractere!");
            }
            if(opcao.charAt(0) != 's' && opcao.charAt(0) != 'n'){
                throw new IllegalArgumentException("Digite apenas 's' ou 'n'!");
            }
    }
}