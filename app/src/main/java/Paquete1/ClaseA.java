package Paquete1;

public class ClaseA {

    public String a;
    protected String b;
    String c;
    private String d;

    public ClaseA() {

        d = "Hola Mundo";
    }

    //Método público

    public String metodoPublico(){
        String string = "Método Público";
        return string;
    }

    //Método protected
    protected String metodoProtected(){
        String string = "Método Protected";
        return string;
    }

    //Método Defult

     String metodoDefult(){
        String string = "Método Defult";
        return string;
    }

    //Método privado

    private String metodoPrivado(){
        String string = "Método Privado";
        return string;
    }
}
