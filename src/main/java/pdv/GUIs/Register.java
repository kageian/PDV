package pdv.GUIs;

import pdv.db.MyJDBC;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Register extends JFrame {
    //Construtor
    public Register() {
        setTitle("Registro");
        BufferedImage imgmonge = null;
        try {
            imgmonge = ImageIO.read(new File("src/main/java/pdv/assets/monge meditation.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }


        setSize(1366, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        Font fontePadrao = new Font("Arial", Font.PLAIN, 15);
        Font fonteLB = new Font("Arial", Font.BOLD, 13);
        Font fonteText = new Font("Arial", Font.PLAIN, 12);

        JLabel lbUsername = new JLabel();

        lbUsername.setBounds(82, 155, 170, 25);
        lbUsername.setText("Username");

        JLabel lbPassword = new JLabel();

        lbPassword.setBounds(82, 215, 170, 25);
        lbPassword.setText("Password");

        JLabel lbConfirmaPassword = new JLabel("Confirm Password");
        lbConfirmaPassword.setBounds(82, 270, 170, 35);
        JLabel lbEmail = new JLabel("Email");
        lbEmail.setBounds(82, 330, 170, 35);


        JLabel lb = new JLabel();
        lb.setBounds(160, 55, 188, 55);
        lb.setText("Register");
        lb.setFont(new Font("Arial", Font.PLAIN, 50));

        JLabel imagemonge = new JLabel();
        imagemonge.setBounds(getWidth() / 2, 0, getWidth(), getHeight());
        System.out.println(getHeight() / 2 + " " + getWidth());


        add(lb);
        add(lbUsername);
        add(lbPassword);
        add(lbConfirmaPassword);
        add(lbEmail);

        //Username
        JTextField usernameField = new JTextField();
        usernameField.setBounds(80, 180, 340, 25);
        usernameField.setFont(fonteText);
        //password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(80, 240, 340, 25);
        setFont(fonteText);

        //confirmar password
        JPasswordField confirmpasswordField = new JPasswordField();
        confirmpasswordField.setBounds(80, 300, 340, 25);
        confirmpasswordField.setFont(fonteText);


        //email
        JTextField emailField = new JTextField();
        emailField.setBounds(80, 360, 340, 25);
        emailField.setFont(fonteText);


        add(passwordField);
        add(confirmpasswordField);
        add(usernameField);
        add(emailField);


        //Forças os componentes a reaparecer
        revalidate();
        repaint();


        if (imgmonge != null){
            Image dimg = imgmonge.getScaledInstance(getWidth() / 2, getHeight() / 2, Image.SCALE_SMOOTH);

            ImageIcon imageIcon = new ImageIcon(dimg);

            imagemonge.setIcon(imageIcon);
        }
        add(imagemonge);

        //aparecer todos os componentes
        setVisible(true);

        //Buttons
        JButton jb = new JButton();
        jb.setText("Voltar");
        jb.setBounds(80, 550, 340, 50);
        jb.setFont(fontePadrao);
        jb.setForeground(new Color(255, 255, 255));
        jb.setBackground(new Color(10, 10, 10));
        add(jb);

        JButton jb2 = new JButton();
        jb2.setText("Registrar");
        jb2.setBounds(80, 450, 340, 50);
        jb2.setFont(fontePadrao);
        jb2.setForeground(new Color(255, 255, 255));
        jb2.setBackground(new Color(51, 86, 51));
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pegando o usuario
                String username = usernameField.getText();

                //Pegando o password e a confirmação de senha

                String password = new String(passwordField.getPassword());

                String confirmarPassword = new String(confirmpasswordField.getPassword());

                // Pegando o email

                String email = emailField.getText();


                //Validando os inputs
                if (validateUserInput(username, password, confirmarPassword, email)) {

                    if (MyJDBC.register(username, password, email)) {
                        JOptionPane.showMessageDialog(Register.this, "Usuario criado! Aproveite o pdv");

                        Register.this.dispose();
                        Login login = new Login();
                        login.setVisible(true);


                    } else {
                        JOptionPane.showMessageDialog(Register.this, "Erro: Usuario ja registrado.");
                    }


                } else {
                    JOptionPane.showMessageDialog(Register.this, "Erro: Senha deve conter mais de 6 digitos\n" +
                            "E/OU as senhas devem se coincidirem");
                }

            }
        });
        add(jb2);
        //Ações
        jb.addActionListener(this::Voltar);
        setVisible(true);
    }

    private void Voltar(ActionEvent ActionEvent) {
        dispose();
        Login login = new Login();

    }

    private boolean validateUserInput(String username, String password, String confirmarPassword, String email) {

        //Não pode ser vazios
        if (username.length() == 0 || password.length() == 0 || confirmarPassword.length() == 0 || email.length() == 0)
            return false;

        //senha tem q ter mais de 6 caracteres

        if (password.length() < 6) return false;
        // senha tem q ser a msm coisa da confirmação de senha
        if (!password.equals(confirmarPassword)) return false;
        //passou da validação
        return true;


    }


}
