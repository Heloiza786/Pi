package senac.java.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
    public class UsuariosDal {
        public Connection  conectar() {
            Connection conexao = null;

            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                String url = "jdbc:sqlserver://localhost:1433;databaseName= mastereg";
                String usuario = "SENACRJEDU/116128412023.1";
                String senha = "senac@12841";

                conexao = DriverManager.getConnection(url, usuario, senha);

                if (conexao != null) {
//                System.out.println("Conexão aceita");
                    return conexao;

                }

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("O erro foi: " + e);

            } finally {

                try {
                    if (conexao != null && !conexao.isClosed()) {
                        conexao.close();
                    }

                } catch (SQLException e) {
                    System.out.println("O erro no finally foi: " + e);
                }

            }

//
            return conexao;
        }
    //INSERIR - CREATE

    public int inserirUsuario( String name, String lastName, String cpf, String email ) throws SQLException{
        String sql = "INSERT INTO Usuarios(name, last_name, cpf, email) VALUES(?,?,?,?)";

        int linhasModf = 0;
        Connection conexao = conectar();

        try(PreparedStatement statement = conectar().prepareStatement(sql)){

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, cpf);
            statement.setString(4, email);


            conectar().close();
            linhasModf = statement.executeUpdate();
            System.out.println("Foram modificadas "+ linhasModf +"no banco de dados");
            conexao.close();
            return linhasModf;

        }catch (SQLException e){
            System.out.println("O erro na inserção foi " + e);
        }
        conexao.close();
        return  linhasModf;
    }

    public ResultSet listarUsuario() throws SQLException{
          String sql = "SELECT * FROM USUARIOS";
          ResultSet resultSet = null;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            resultSet = statement.executeQuery();

            System.out.println("Listagem dos usuarios: ");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String cpf = resultSet.getString("cpf");
                String email= resultSet.getString("email");

                System.out.println("id" + id);
                System.out.println("name" + name);
                System.out.println("lastName" + lastName);
                System.out.println("cpf" + cpf);
                System.out.println("email" + email);
                System.out.println(" " );

            }
            return resultSet;

        }catch (SQLException e){
            System.out.println("O erro na listagem de usuario fo " + e);


        }
        return resultSet;

        }

        public int  atualizarUsuario() throws  Exception{
            String sql = "UPDATE Usuarios SET (name = ?, lastName = ?, cpf = ?, email = ?, WHERE id = ?)";
            int linhasModf = 0;
            Connection conexao = conectar();

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
//            statement.setString(1, name);
//            statement.setString(2, lastName);
//            statement.setString(3, cpf);
//            statement.setString(4, email);


            linhasModf = statement.executeUpdate();

            System.out.println("Foram modificadas" + linhasModf + "no banco de dados");

        return linhasModf;
        }catch (SQLException e){
            System.out.println("O erro na atualização de a dados de usuario fo " + e);

        }
            return linhasModf;
        }
        public int excluirUsuario(int id) throws SQLException{
        String sql ="DELETE FROM Usuarios WHERE id = ?";
        int linhasModf =0;
        try (PreparedStatement statement = conectar().prepareStatement(sql)){

            linhasModf = statement.executeUpdate();
            System.out.println("Foram modificadas" + linhasModf + "no banco de dados");

        }
        catch(SQLException e){
            System.out.println("O erro na deletacão de dados de usuario foi " + e);
        }
            return linhasModf;
        }
    }
