package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import senac.java.Domain.Venda;
import senac.java.Services.ResponseEndPoints;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendaController {
    public static List<Venda> vendaList = new ArrayList<>();
    static ResponseEndPoints res = new ResponseEndPoints();

    public static class ProductsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            if ("GET".equals(exchange.getRequestMethod())) {
                List<Venda> getAllFromArray = Venda.getAllVenda(vendaList);
                Venda vendaJson = new Venda();


                if (!getAllFromArray.isEmpty()) {
                    for (Venda venda : getAllFromArray) {

                        System.out.println("users:" + venda.getUser());
                        System.out.println("products:" + venda.getProducts());
                        System.out.println("finishedSale:" + venda.getFinishedSales());
                        System.out.println("discount:" + venda.getDiscount());
                        System.out.println("Sale:" + venda.getSale());
                        System.out.println("filters" + venda.getFilters());
                        System.out.println();
                        System.out.println("-----------------------------------------------------");
                        System.out.println();

                    }

                    res.enviarResponseJson(exchange, vendaJson.arrayToJson(getAllFromArray), 200);


                } else {

                    String response = "Dados não encontrados!";
                    res.enviarResponse(exchange, response, 200);
                }


            } else if ("POST".equals(exchange.getRequestMethod()))
            {
                try (InputStream requestBody = exchange.getRequestBody()) {
                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));


                    Venda venda = new Venda(
                            json.getString("user"),
                            json.getString("products"),
                            json.getString("finishedSale"),
                            json.getString("discount"),
                            json.getString("Sale"),
                            json.getString("filters")


                    );

                    vendaList.add(venda);
                    System.out.println(vendaList);
                    res.enviarResponseJson(exchange,venda.toJson(vendaList), 201);

                } catch (Exception e) {
                    System.out.println("Cheguei no Catch do POST");
                    String response = e.toString();

                    System.out.println(response);
                    System.out.println("--------------------");
                    res.enviarResponse(exchange, response, 405);
                }
            }else if ("OPTIONS" .equals(exchange.getRequestMethod())){
                exchange.sendResponseHeaders(204,-1);
                exchange.close();
                return;
            }
        }
    }}
