public class app {
    public static void main(String[] args){
        ControladorDeEstoque controlador = new ControladorDeEstoque();
        controlador.adicionarProduto();
        controlador.excluirProduto(1000);
    }
}
