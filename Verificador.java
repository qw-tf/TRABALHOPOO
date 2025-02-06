import java.time.format.DateTimeParseException;

import Excessoes.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//CLASSE CRIADA PARA FACILITAR E PREVENIR MUITA REPETICAO DE CODIGO DESNESCESSARIA!!!!
public class Verificador {
    
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

    public void verificarCodigo(int codigo) throws InvalidCodigoException{
        if(codigo < 1000 && codigo > 9999){ // checa se o codigo é valido (possue 4 digitos)
            throw new InvalidCodigoException("Digite um codigo valido!");
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

    public void verificarQuantidade(int quantidade, int limiteEstoque) throws InvalidQuantidadeException{
            if(quantidade < 0 || quantidade > limiteEstoque){
                throw new InvalidQuantidadeException("Quantidade Invalida!");
            }
    }

    public void verificarLimiteDeEstoque(int limiteEstoque, int quantidade) throws LimiteEstoqueException{
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