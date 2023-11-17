package senac.java.Controllers;

import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import senac.java.DAL.UsuariosDal;
import senac.java.Domain.Usuarios;
import senac.java.Domain.Venda;
import senac.java.Services.ResponseEndPoints;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.sun.net.httpserver.HttpExchange;




public class UserController {
    public static List<Usuarios> usersList = new ArrayList<>();
    static ResponseEndPoints res = new ResponseEndPoints();
     public static String response = "";
    public static class UserHandler implements HttpHandler{


        @Override
        public void handle(HttpExchange exchange) throws IOException{

            if("GET".equals(exchange.getRequestMethod())) {
                doGet(exchange );
            }else if ("POST".equals(exchange.getRequestMethod())) {
                doPost(exchange);
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                doPut(exchange);
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                doDel(exchange);
            }
        }

    }public static void doGet(HttpExchange exchange) throws IOException{
        UsuariosDal usuariosDal = new UsuariosDal();
            try(InputStream requestBody = exchange.getRequestBody()){
                usuariosDal.listarUsuario();
                JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                int idDel = Integer.parseInt(json.getString("id"));
                usuariosDal.excluirUsuario(idDel);
            } catch (Exception e){
                System.out.println("O erro foi :" + e);
            }
        }


        
     public static void doPost(HttpExchange exchange) throws IOException{
        UsuariosDal usuariosDal = new UsuariosDal();
    if ("POST".equals(exchange.getRequestMethod())) {


        try (InputStream requestBody = exchange.getRequestBody()) {
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp =0;

            Usuarios user = new Usuarios(

                    json.getString("name"),
                    json.getString("lastName"),
                    json.getString("cpf"),
                    json.getString("email")

            );
            usersList.add(user);

            resp= usuariosDal.inserirUsuario(user.name, user.lastName, user.cpf , user.email);

            response = "Dados enviados com sucesso";


            res.enviarResponseJson(exchange, user.toJson(),200);
            if(resp == 0){
                response = "houve um problema ao criar o usuario";
            }else{
                response = "Usuario nao encontrado";
            }

        } catch (Exception e) {
            String resposta = e.toString();
            res.enviarResponse(exchange, resposta, 200);

        }}
        } public static void doPut(HttpExchange exchange) throws IOException{
            UsuariosDal usuariosDal = new UsuariosDal();

            try(InputStream requestBody = exchange.getRequestBody()){
                JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
                usuariosDal.atualizarUsuario();

                int idDel = Integer.parseInt(json.getString("id"));
                usuariosDal.atualizarUsuario();
            } catch (Exception e){
                System.out.println("O erro foi :" + e);
            }

            res. enviarResponse(exchange, response,200);
            response = "Essa e a rota de usuario - PUT";

        }public static void doDel(HttpExchange exchange) throws IOException{

                UsuariosDal usuariosDal = new UsuariosDal();
                try(InputStream requestBody = exchange.getRequestBody()){
                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));


                    int idDel = Integer.parseInt(json.getString("id"));
                    usuariosDal.excluirUsuario(idDel);
                }catch (Exception e ){
                    System.out.println("O erro foi :" + e);
                }
                res. enviarResponse(exchange, response,200);
                response = "Essa e a rota de usuario - DELETE";

        }
    }

