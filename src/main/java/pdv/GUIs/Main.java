package pdv.GUIs;

import com.mysql.cj.log.Log;
import pdv.db.MyJDBC;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new cadastrarProduto().setVisible(true);
        //Test para ver se o usuario esta ou não na db
//        System.out.println(MyJDBC.checkUser("username12"));

        //Checando a criação de um novo usuario
//        System.out.println(MyJDBC.register("luiz", "12345678", "luiz@gmail.com"));

        //Checando a validação do usuario
//        System.out.println(MyJDBC.validateLogin("luiz2","12345678"));
    }
}
