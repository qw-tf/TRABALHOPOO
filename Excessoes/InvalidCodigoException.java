package Excessoes;
public class InvalidCodigoException extends Exception{
    public InvalidCodigoException(){
    }

    public InvalidCodigoException(String msg){
        super(msg);
    }
}
