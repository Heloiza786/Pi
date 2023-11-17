package senac.java.Domain;

import org.json.JSONObject;

import java.util.List;
import java.util.logging.Filter;

public class Venda {
    int id;
    public String user = "";
    public String products = "";
    public String finishedSale = "";
    public String discount = "";
    public String Sale = "";

    public String filters = "";



    public Venda(){

    }
    public Venda(String user, String products,
                 String finishedSale, String discount, String Sale ,String filters){
        this.user = user;
        this.products = products;
        this.finishedSale = finishedSale;
        this.discount = discount;
        this.Sale = Sale;
        this.filters = filters;
    }
    public String  getUser() {return user;}
    public void setUser (String name){
        this.user = user;
}

    public String getProducts() {return products;}
    public void setProducts(String products){
        this.products = products;
}
    public String getFinishedSales() {return finishedSale;}
    public void setFinishedSales(String finishedSale){
        this.finishedSale = finishedSale;
    }

    public String getDiscount(){return discount;}
    public void setDiscount(String discount){
        this.discount = discount;
    }

    public String getSale(){return Sale;}
    public void setSale(String sale){ this.Sale = sale;}

    public String getFilters(){return filters;}
    public void setFilters(String filters){ this.filters = filters;}

    public  JSONObject toJson(List<Venda> arrayToJson){
        JSONObject json = new JSONObject();

        json.put("user", user);
        json.put("products",products);
        json.put("finishedSale", finishedSale);
        json.put("discount", discount);
        json.put("Sale", Sale);
        json.put("filters" , filters);
                return json;
    }
    public JSONObject arrayToJson(List<Venda>vendaListArray){
        JSONObject json = new JSONObject();

            if (!vendaListArray.isEmpty()) {
                var keyJson = 0;
                for (Venda venda : vendaListArray) {
                    JSONObject jsonObjectPut = new JSONObject();


                    jsonObjectPut.put("user" , venda.getUser());
                    jsonObjectPut.put("products" , venda.getProducts());
                    jsonObjectPut.put("finishedSale" , venda.getFinishedSales());
                    jsonObjectPut.put("discount" , venda.getDiscount());
                    jsonObjectPut.put("Sale" , venda.getSale());
                    jsonObjectPut.put("filters" , venda.getFilters());



                    json.put(String.valueOf(keyJson),jsonObjectPut);
                    keyJson++;


                }
                    return json;

                } else{
                    return null;
                }
    }

    public static  Venda getVenda(int index, List<Venda> vendaList ) {
        if (index >= 0 && index < vendaList.size()) {
            return vendaList.get((index));
        } else {
            return null;
        }
    }
    public static List<Venda> getAllVenda(List<Venda> vendaList  ){
            return vendaList;
    }
}