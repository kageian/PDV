package pdv.db;

public class Produtos {

    private String code;
    private String name;
    private double precokg;
    private double preco;

    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }

    public double getPrecokg(){
        return precokg;
    }

    public double getPreco(){
        return preco;
    }

    public void setCode(String code){
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrecokg(double precokg){
        this.precokg = precokg;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
