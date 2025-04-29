package pdv.GUIs;

import pdv.db.MyJDBC;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class Login extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    private boolean isdarkmode;
    JButton DarkModeButton, jb2, jb;
    JLabel lb, copy, lbUsername, lbPassword;


    public Login() {
        setTitle("Login");
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

        //buttons
        jb = new JButton("Entrar");
        jb.setBounds(80, 400, 340, 50);
        jb.setFont(fontePadrao);
        jb.setForeground(new Color(255, 255, 255));
        jb.setBackground(new Color(65, 128, 65));
        jb.addActionListener(this::Entrar);

        jb2 = new JButton();
        jb2.setText("Registrar-se");
        jb2.setBounds(185, 480, 130, 30);
        jb2.setFont(fontePadrao);
        jb2.setForeground(new Color(255, 255, 255));
        jb2.setBackground(new Color(44, 73, 44));

        add(jb2);
        add(jb);

        DarkModeButton = new JButton("Modo Escuro");
        DarkModeButton.setBounds(1220, 35, 100, 50);

        add(DarkModeButton);

        jb2.addActionListener(this::Registra);
        DarkModeButton.addActionListener(this::toggle);

        //Labels
        lbUsername = new JLabel();

        lbUsername.setBounds(80, 210, 170, 25);
        lbUsername.setText("Username");

        lbPassword = new JLabel();

        lbPassword.setBounds(82, 310, 170, 25);
        lbPassword.setText("Password");

        lb = new JLabel();

        lb.setBounds(190, 55, 124, 55);
        lb.setText("Login");
        lb.setFont(new Font("Arial", Font.PLAIN, 50));

        JLabel imagemonge = new JLabel();
        imagemonge.setBounds(getWidth() / 2, 0, getWidth(), getHeight());


        copy = new JLabel("@ Copyright LuizLHDev");
        copy.setBounds(190, 545, 130, 25);


        add(lb);
        add(lbUsername);
        add(lbPassword);
        add(copy);

        //txtfiel Username
        usernameField = new JTextField();
        usernameField.setBounds(80, 240, 340, 25);
        usernameField.setFont(fonteText);

        //txtpassword Password
        passwordField = new JPasswordField();
        passwordField.setBounds(80, 340, 340, 25);
        setFont(fonteText);


        add(passwordField);
        add(usernameField);

        passwordField.addActionListener(this::Entrar);
        usernameField.addActionListener(this::Entrar);

        //Forças os componentes a reaparecer
        revalidate();
        repaint();


        if (imgmonge != null) {
            Image dimg = imgmonge.getScaledInstance(getWidth() / 2, getHeight() / 2, Image.SCALE_SMOOTH);

            ImageIcon imageIcon = new ImageIcon(dimg);

            imagemonge.setIcon(imageIcon);
        }
        add(imagemonge);

        //aparecer todos os componentes
        setVisible(true);

    }


    private void toggle(ActionEvent actionEvent) {
        isdarkmode = !isdarkmode;
        DarkModeButton.setText(isdarkmode ? "Modo Claro" : "Modo Escuro");
        applyTheme();
    }

    private void applyTheme() {
        Color backgroundColor = isdarkmode ? new Color(40, 42, 54, 255) : new Color(240, 240, 240);
        Color textColor = isdarkmode ? new Color(220, 220, 220) : new Color(0, 0, 0);
        Color buttonsColor = isdarkmode ? new Color(40, 42, 54, 255) : new Color(255, 255, 255);
        Color loginButton = isdarkmode ? new Color(40, 42, 54, 255) : new Color(65, 128, 65);
        Color registerButton = isdarkmode ? new Color(40, 42, 54, 255) : new Color(44, 73, 44);

        getContentPane().setBackground(backgroundColor);

        lb.setForeground(textColor);
        lbPassword.setForeground(textColor);
        lbUsername.setForeground(textColor);
        copy.setForeground(textColor);
        usernameField.setForeground(textColor);
        usernameField.setBackground(backgroundColor);
        passwordField.setBackground(backgroundColor);
        passwordField.setForeground(textColor);
        if (isdarkmode) {
            usernameField.setBorder(new LineBorder(new Color(124, 122, 122)));
            passwordField.setBorder(new LineBorder(new Color(124, 122, 122)));
        } else {
            usernameField.setBorder(new LineBorder(new Color(0, 0, 0)));
            passwordField.setBorder(new LineBorder(new Color(0, 0, 0)));
        }
        //buttons
        jb.setForeground(textColor);
        jb.setBackground(loginButton);
        jb2.setBackground(registerButton);
        jb2.setForeground(textColor);
        DarkModeButton.setBackground(buttonsColor);
        DarkModeButton.setForeground(textColor);


    }


    private void Registra(ActionEvent actionEvent) {
        dispose();
        new Register().setVisible(true);

    }

    private void Entrar(ActionEvent actionEvent) {


        String username = usernameField.getText();

        String password = new String(passwordField.getPassword());

        if (MyJDBC.validateLogin(username, password)) {
            Login.this.dispose();
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(Login.this, "Falha ao logar...");
        }
    }


        //Colocar para todos os botões que n recebem um metodo proprio receba esta função.
        @Override
        public void actionPerformed (ActionEvent e){
            JOptionPane.showMessageDialog(null, "Erro ai inicializar", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
