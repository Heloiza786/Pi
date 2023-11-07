package senac.java.Domain;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    int id;
    String name = "";
    String factory = "";
    String quantity = "";

    public Estoque(){

    }
    public Estoque(String name, String factory, String quantity){
        this.name = name;
        this.factory = factory;
        this.quantity= quantity;

    }

    public String  getName() {return name;}
    public void setName (String name){
        this.name = name;
    }

    public String getFactory() {return factory;}
    public void setFactory(String products){this.factory = factory;}

    public String getQuantity() {return quantity;}
    public void setQuantity(String quantity ){this.quantity = quantity;}



    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("factory", factory);
        json.put("quantity", quantity);


        return json;
    }
    public JSONObject arrayToJson(List<Estoque> estoqueListArray) {
        JSONObject json = new JSONObject();

        if (!estoqueListArray.isEmpty()) {
            var keyJson = 0;
            for (Estoque estoque : estoqueListArray) {
                JSONObject jsonObjectPut = new JSONObject();


                jsonObjectPut.put("name", estoque.getName());
                jsonObjectPut.put("factory", estoque.getFactory());
                jsonObjectPut.put("quantity", estoque.getQuantity());


                json.put(String.valueOf(keyJson), jsonObjectPut);
                keyJson++;


            }
            return json;

        } else {
            return null;
        }

        } public static List<Estoque> getAllEstoque(List<Estoque> estoqueList ){
            return  estoqueList;
        }
    }

//    public ArrayList userList(){
//        ArrayList list = new ArrayList();
//        list.getClass();
//
//        return list;


