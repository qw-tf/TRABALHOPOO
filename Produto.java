public class Produto{
    //atributos pedidos no arquivo,
    //escolhemos adicionar código do produto e limite maximo de estoque.
    private String nome, descricao;
    private int quantidade, codigo, limiteEstoque;
    private double preco;

    //variavel static para contar a quantidade total de produtos, 
    private static int quantidadeDeProdutosTotal = 0;

    //instancia novo verificador para checar as variaveis
    Verificador verificador = new Verificador();

    //construtor
    public Produto(String nome, int quantidade, double preco, int limiteEstoque){
      
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.limiteEstoque = limiteEstoque;
        codigo = quantidadeDeProdutosTotal + 1000;
    }

    //gets e sets dos atributos
    public int getQuantidade() {
        return quantidade;
    }
    public int getCodigo() {
        return codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public int getLimiteDeEstoque(){
        return limiteEstoque;
    }
    public static int getQuantidadeDeProdutosTotal() {
        return quantidadeDeProdutosTotal;
    }
    public int getLimiteEstoque() {
        return limiteEstoque;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setNome(String nome) throws InvalidNameException{
        verificador.verificarNome(nome);
        this.nome = nome;
    }
    public void setPreco(double preco) throws InvalidPrecoException{
        verificador.verificarPreco(preco);
        this.preco = preco;
    }
    public void setQuantidade(int quantidade) throws InvalidQuantidadeException{
        verificador.verificarQuantidade(quantidade);
        this.quantidade = quantidade;
    }
    public void setLimiteDeEstoque(int limiteEstoque) throws LimiteEstoqueException{
        verificador.verificarLimiteDeEstoque(limiteEstoque);
        this.limiteEstoque = limiteEstoque;
    }

    //para poder fazer mudanças na quantidade total de produtos
    public static void aumentarProdutosTotal(){
        quantidadeDeProdutosTotal++;
    }
    public static void diminuirProdutosTotal(){
        quantidadeDeProdutosTotal--;
    }
}