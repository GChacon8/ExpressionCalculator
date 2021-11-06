import java.io.Serializable;


/**
 * Intermediario para facilitar comunicación entre el cliente y el servidor. Contiene la expresión y el ID del cliente específico
 * @author Gabriel Chacon Alfaro
 */
public class Mensaje implements Serializable{

    private String expresion;
    private String identificador;
    private int tipo;

    /**
     * Crea una instancia e inicializa sus atributos
     * @param expresion La expresión matemática ingresada en la interfaz
     * @param tipo Un número característico
     * @param identificador El identificador propio de cada instancia
     */
    public Mensaje (String expresion, int tipo, String identificador){
        this.expresion = expresion;
        this.tipo = tipo;
        this.identificador = identificador;
    }

    /**
     * Obtiene la expresión que contiene un Mensaje
     * @return Retorna la expresión dentro del mensaje
     */
    public String getExpresion(){
        return this.expresion;
    }

    /**
     * Obtiene el valor del tipo que contiene un Mensaje
     * @return Retorna el valor del tipo dentro del mensaje
     */
    public int getTipo(){
        return this.tipo;
    }

    /**
     * Obtiene la expresion que contiene un Mensaje
     * @return Retorna la expresion dentro del mensaje
     */
    public String getIdentificador(){
        return this.identificador;
    }

    /**
     * Obtiene el mensaje como un String separado por comas.
     * @return Retorna Todo el mensaje como un String separado por comas.
     */
    public String toString(){
        return expresion + " , " + tipo + " , " + identificador;
    }

}