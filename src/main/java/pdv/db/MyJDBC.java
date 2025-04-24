package pdv.db;

import pdv.constants.CommonConstants;

import java.sql.*;

public class MyJDBC {
    //registrando novos usuarios no banco de dados
    //true - sucesso ao registrar
    //false - falha ao registrar
    public static boolean register(String username, String password, String email) {

        try {
            //primeiro check se o usuario ja existe no banco de dados
            if (!checkUser(username)) {
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                // preparando a query para ser inseridos novos usuarios. Os ? ? servem como placeholds
                // para onde vai ser inseridos os valores do usuario
                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " +CommonConstants.DB_USERS_TABLE_NAME + "(username, password, email)"+
                                "VALUES(?, ?, ?)"
                );
                //Demarcando onde ficara cada valor, o username no primeiro ? e o password no segundo ?
                insertUser.setString(1,username);
                insertUser.setString(2,password);
                insertUser.setString(3,email);

                // executando para inserir novos usuarios
                insertUser.executeUpdate();
                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    //checando se o usuario ja existe
    //true - user não existe
    // false - user ja existe

    public static boolean checkUser(String username) {
        try {

            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            PreparedStatement checkUserExist = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ?"
            );
            checkUserExist.setString(1, username);
            ResultSet resultSet = checkUserExist.executeQuery();

            //Checando se o resultado setado é vazio

            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    //Verificando se o login e a senha são iguais para conseguir logar
    // true para poder entrar, tem você no banco de dados
    // false para não tem no banco de dados n tem acesso!
    public static boolean validateLogin (String username,String password){
        try{
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME,CommonConstants.DB_PASSWORD);

            //Criando uma select query

            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM "+CommonConstants.DB_USERS_TABLE_NAME+
                            " WHERE USERNAME = ? AND PASSWORD = ?"
            );

            validateUser.setString(1, username);
            validateUser.setString(2, password);


            ResultSet resultSet = validateUser.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return false;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
