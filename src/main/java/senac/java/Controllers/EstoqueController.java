package senac.java.Controllers;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import senac.java.Domain.Estoque;
import senac.java.Domain.Usuarios;
import senac.java.Domain.Venda;
import senac.java.Services.ResponseEndPoints;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EstoqueController {
    static ResponseEndPoints res = new ResponseEndPoints();

    public static List<Estoque> estoqueList = new ArrayList<>();

    public static class EstoqueHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException{
            String response = "";

            if("GET".equals(exchange.getRequestMethod())){
                List<Estoque> getAllFromArray = Estoque.getAllEstoque(estoqueList);
//                response = "Essa é a rota de Estoque - GET";
//
//                res. enviarResponse(exchange, response,200);
//                estoqueList.reversed();


                if (!getAllFromArray.isEmpty()) {

                    for (Estoque estoque : getAllFromArray) {

                        System.out.println("name" + estoque.getName());
                        System.out.println("factory" + estoque.getFactory());
                        System.out.println("quantity" + estoque.getQuantity());

                        System.out.println("-----------------------------------------------------");
                        System.out.println();

                    }

                    res.enviarResponse(exchange, response, 200);

                }
            }else if ("POST".equals(exchange.getRequestMethod())){
            System.out.println("Teste post Estoque");


                try (InputStream requestBody = exchange.getRequestBody()) {
                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));


                   Estoque estoque = new Estoque(

                            json.getString("name"),
                            json.getString("factory"),
                            json.getString("quantity")


                    );
                    estoqueList.add(estoque);

                    res.enviarResponseJson(exchange, estoque.arrayToJson(estoqueList) ,200);


                } catch (Exception e) {
                    String resposta = e.toString();
                    res.enviarResponse(exchange, resposta, 405);


                }

//            } else if ("PUT".equals(exchange.getRequestMethod())) {
//                response = "Essa e a rota de estoque - PUT";
//                res. enviarResponse(exchange, response,200);
//            } else if ("DELETE".equals(exchange.getRequestMethod())) {
//                response = "Essa e a rota de estoque - DELETE";
//                res. enviarResponse(exchange, response,200);
//            }else {
//                response = "Rota estoque - ERRO!" +
//                        " O metodo utilizado foi: " + exchange.getRequestMethod();;
//                res. enviarResponse(exchange, response,405);
            }
        }

    }
}
