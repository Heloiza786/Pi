package senac.java.Domain;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Usuarios {
    int id;
    public String name = "";
    public String lastName = "";
    public String cpf = "";
    public String email = "";

    public Usuarios() {

    }

    public Usuarios(String name, String lastName,
                    String cpf, String email) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;


    }

    public String getUser() {
        return name;
    }

    public void setUser(String name) {
        this.name = name;
    }

    public String getProducts() {
        return lastName;
    }

    public void setProducts(String products) {
        this.lastName = lastName;
    }

    public String getFinishedSales() {
        return cpf;
    }

    public void setFinishedSales(String finishedSale) {
        this.cpf = cpf;
    }

    public String getDiscount() {
        return email;
    }

    public void setDiscount(String discount) {
        this.email = email;
    }

    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("lastName", lastName);
        json.put("cpf", cpf);
        json.put("email", email);

        return json;
    }

    public JSONObject arrayToJson(List<Venda> vendaListArray) {
        JSONObject json = new JSONObject();

        if (!vendaListArray.isEmpty()) {
            var keyJson = 0;
            for (Venda venda : vendaListArray) {
                JSONObject jsonObjectPut = new JSONObject();


                jsonObjectPut.put("name", venda.getUser());
                jsonObjectPut.put("lastName", venda.getProducts());
                jsonObjectPut.put("cpf", venda.getFinishedSales());
                jsonObjectPut.put("email", venda.getDiscount());


                json.put(String.valueOf(keyJson), jsonObjectPut);
                keyJson++;


            }
            return json;

        } else {
            return null;
        }
    }

    public ArrayList userList() {
        ArrayList list = new ArrayList();
        list.getClass();

        return list;
    }

}

