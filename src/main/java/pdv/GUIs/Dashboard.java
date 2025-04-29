package pdv.GUIs;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import pdv.db.Produtos;

public class Dashboard extends JFrame {
    JLabel lbCodigo, lbPreco, lbQTD, lbPrecoTotal, lbValorTotal, lbTroco, lbTrocoNum, lbPrecoKg, lbNome, lbTotal, lbPrecoNum, lbPrecoKgNum, lbPrecoReal, lbPrecoKgReal;
    JTextField codigoField, qtdField, valorPagoField;
    JTextArea areaProdutos;
    JScrollPane scroll;
    JButton modos;

    private boolean isdarkmode;
    private double total = 0;
    private static final String CAMINHO_JSON = "produtos.json";

    private void reset() {
        try {
            if (this.codigoField != null) {
                codigoField.setText("");
                areaProdutos.setText("");
                lbPrecoNum.setText("0");
                lbPrecoKgNum.setText("0");
                qtdField.setText("");
                valorPagoField.setText("");
                lbTrocoNum.setText("0");
                lbTotal.setText("0");
                System.out.println("Produtos resetados com sucesso");


            } else {
                throw new NullPointerException("Numero do preço e preço por kg não inicializado");
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Campos já resetados.");
            e.printStackTrace();
        }
    }


    public Dashboard() {
        setTitle("Dashboard");
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);

        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50));
        //icones

        ImageIcon iconeluaoriginal = new ImageIcon("src/main/java/pdv/assets/lua.png");

        Image imagemlua = iconeluaoriginal.getImage();
        Image iconeluaredimensionada = imagemlua.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon iconelua = new ImageIcon(iconeluaredimensionada);

        BufferedImage imglogo = null;
        try {
            imglogo = ImageIO.read(new File("src/main/java/pdv/assets/monge meditation.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        Font fonteTexto = new Font("Arial", Font.PLAIN, 24);
        Font fonteLabel = new Font("Arial", Font.PLAIN, 20);
        Font fonteDireitos = new Font("Arial", Font.PLAIN, 29);
        Font fonteResultados = new Font("Arial", Font.BOLD, 34);

        //LABELS
        JLabel imageLogo = new JLabel();
        imageLogo.setBounds(30, 20, 200, 200);

        lbNome = new JLabel("");
        lbNome.setBounds(325, 70, 300, 52);
        lbNome.setFont(new Font("Arial", Font.BOLD, 30));

        lbCodigo = new JLabel("Codigo");
        lbCodigo.setBounds(325, 150, 100, 30);
        lbCodigo.setFont(fonteLabel);

        lbPreco = new JLabel("Preço");
        lbPreco.setBounds(326, 250, 85, 30);
        lbPreco.setFont(fonteLabel);

        lbPrecoNum = new JLabel("0");
        lbPrecoNum.setBounds(370, 290, 300, 30);
        lbPrecoNum.setFont(fonteResultados);

        lbPrecoReal = new JLabel("R$");
        lbPrecoReal.setBounds(325, 290, 300, 30);
        lbPrecoReal.setFont(fonteResultados);

        lbPrecoKgReal = new JLabel("R$");
        lbPrecoKgReal.setBounds(325, 390, 300, 30);
        lbPrecoKgReal.setFont(fonteResultados);


        lbPrecoKg = new JLabel("Valor por KG");
        lbPrecoKg.setBounds(326, 350, 120, 30);
        lbPrecoKg.setFont(fonteLabel);

        lbPrecoKgNum = new JLabel("0");
        lbPrecoKgNum.setBounds(370, 390, 300, 30);
        lbPrecoKgNum.setFont(fonteResultados);

        lbQTD = new JLabel("Quantidade");
        lbQTD.setBounds(326, 450, 200, 45);
        lbQTD.setFont(fonteLabel);

        lbPrecoTotal = new JLabel("Total R$");
        lbPrecoTotal.setBounds(950, 600, 200, 45);
        lbPrecoTotal.setFont(fonteDireitos);

        lbTotal = new JLabel("0");
        lbTotal.setBounds(1065, 600, 200, 45);
        lbTotal.setFont(fonteDireitos);

        lbValorTotal = new JLabel("Valor Pago R$");
        lbValorTotal.setBounds(730, 480, 300, 45);
        lbValorTotal.setFont(fonteDireitos);

        lbTroco = new JLabel("Troco R$");
        lbTroco.setBounds(1055, 480, 200, 45);
        lbTroco.setFont(fonteDireitos);

        lbTrocoNum = new JLabel("0");
        lbTrocoNum.setBounds(1175, 480, 200, 45);
        lbTrocoNum.setFont(fonteDireitos);

        areaProdutos = new JTextArea();
        areaProdutos.setEditable(false);
        areaProdutos.setFont(new Font("Monospaced", Font.PLAIN, 14));

        scroll = new JScrollPane(areaProdutos);
        scroll.setBounds(650, 70, 700, 410);


        add(scroll);
        add(lbPrecoReal);
        add(lbCodigo);
        add(lbQTD);
        add(lbPreco);
        add(lbQTD);
        add(lbPrecoTotal);
        add(lbTotal);
        add(lbValorTotal);
        add(lbTroco);
        add(lbTrocoNum);
        add(lbPrecoKg);
        add(lbNome);
        add(lbPrecoNum);
        add(lbPrecoKgNum);
        add(lbPrecoKgReal);


        //Inputs
        codigoField = new JTextField();

        codigoField.setBounds(323, 180, 300, 30);
        codigoField.setFont(fonteTexto);

        qtdField = new JTextField();
        qtdField.setBounds(323, 480, 300, 30);
        qtdField.setFont(fonteTexto);

        valorPagoField = new JTextField();
        valorPagoField.setBounds(950, 485, 100, 35);
        valorPagoField.setFont(new Font("Arial", Font.PLAIN, 25));

        add(codigoField);
        add(qtdField);
        add(valorPagoField);

        codigoField.addActionListener(gite -> {
            adicionarProduto();
        });


        JButton sair = new JButton("x");
        sair.setBounds(1290, 0, 50, 50);
        sair.setFont(new Font("Arial", Font.BOLD, 40));
        sair.setForeground(new Color(168, 19, 19));
        sair.setBackground(new Color(0x000000));

        JButton voltar = new JButton("<");
        voltar.setBounds(1200, 0, 50, 50);
        voltar.setFont(new Font("Arial", Font.BOLD, 30));
        voltar.setForeground(new Color(18, 73, 154));
        voltar.setBackground(new Color(0x000000));

        modos = new JButton();
        modos.setBounds(230, 30, 50, 50);
        modos.setIcon(iconelua);
        modos.setHorizontalAlignment(SwingConstants.CENTER);
        modos.setVerticalAlignment(SwingConstants.CENTER);
        modos.setBackground(new Color(0, 0, 0, 0));


        // Atalhos do teclado
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), "abrirTela");
        getRootPane().getActionMap().put("abrirTela", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cadastrarProduto().setVisible(true);
            }
        });
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "resetarCompras");
        getRootPane().getActionMap().put("resetarCompras", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), "mostrarProdutos");
        getRootPane().getActionMap().put("mostrarProdutos", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProdutosCadastrados().setVisible(true);
            }
        });


        add(sair);
        add(voltar);
        add(modos);
        modos.addActionListener(this::toggle);
        sair.addActionListener(this::Sair);
        voltar.addActionListener(this::Voltar);

        qtdField.addActionListener(e -> {
            try {
                double precoValor = Double.parseDouble(lbPrecoNum.getText().trim().replace(",", ".")); //Serve para o texto digitado em precovalor
                int quantidadeValor = Integer.parseInt(qtdField.getText());
                double precoValorkg = Double.parseDouble(lbPrecoKgNum.getText().trim().replace(",", ".")); //Serve para o texto digitado em precovalor

                if (precoValor > 0) {
                    double TotalPagar = precoValor * quantidadeValor;
                    total += TotalPagar;
                    lbTotal.setText(String.format("%.2f", total));


                } else if (precoValorkg > 0) {
                    double TotalPagar = precoValorkg * quantidadeValor;
                    total += TotalPagar;
                    lbTotal.setText(String.format("%.2f", total));

                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto");

                }

                File arquivo = new File(CAMINHO_JSON);
                try {

                    // Transforma um json em lista
                    ObjectMapper mapper = new ObjectMapper();
                    Produtos[] produtosArray = mapper.readValue(arquivo, Produtos[].class);
                    List<Produtos> produtos = Arrays.asList(produtosArray);

                    for (Produtos p : produtos) {
                        if (p.getCode().equalsIgnoreCase(codigoField.getText())) {
                            String resultados = "Nome: " + p.getName() + " Codigo: " + p.getCode() + " Preço: " + p.getPreco() + " Preco por KG: " + p.getPrecokg() + " " + " Quantidade: " + qtdField.getText() + "\n";
                            areaProdutos.append(resultados);

                            return;
                        }

                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }


            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite apenas numeros", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        valorPagoField.addActionListener(e -> {
            try {
                double valorPago = Double.parseDouble(lbTotal.getText().replace(",", ".").trim());
                double valorDigitado = Double.parseDouble(valorPagoField.getText());


                if (valorPago < valorDigitado) {

                    double resultado = valorDigitado - valorPago;
                    lbTrocoNum.setText(String.format("%.2f", resultado));
                } else if (valorPago > valorDigitado) {
                    double faltando = valorPago - valorDigitado;
                    JOptionPane.showMessageDialog(null, "Esta faltando " + "R$" + faltando, "ERROR", JOptionPane.ERROR_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "Compra realizada com sucesso");
                    reset();
                }


            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite apenas numeros", "ERROR", JOptionPane.ERROR_MESSAGE);


            }

        });


        if (imglogo != null) {
            Image dimg = imglogo.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            imageLogo.setIcon(imageIcon);
        }
        add(imageLogo);
        setVisible(true);


    }

    private void adicionarProduto() {
        String codigoDigitado = codigoField.getText();
        try {
            File arquivo = new File(CAMINHO_JSON);
            ObjectMapper mapper = new ObjectMapper();

            if (!arquivo.exists() || arquivo.length() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum produto registrado ainda. Aperte f2 para registrar", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
            Produtos[] produtosArray = mapper.readValue(arquivo, Produtos[].class);
            List<Produtos> produtos = Arrays.asList(produtosArray);

            for (Produtos p : produtos) {
                if (p.getCode().equalsIgnoreCase(codigoDigitado)) {
                    lbNome.setText(p.getName());
                    lbPrecoNum.setText(String.format("%.2f", p.getPreco()));
                    lbPrecoKgNum.setText(String.format("%.2f", p.getPrecokg()));
                    return;
                }

            }
            JOptionPane.showMessageDialog(null, "Produto não encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void Voltar(ActionEvent actionEvent) {
        Dashboard.this.dispose();
        Login login = new Login();
        login.setVisible(true);
    }

    private void Sair(ActionEvent actionEvent) {
        dispose();

    }

    private void trocardeCor() {

        Color labels = isdarkmode ? new Color(255, 255, 255, 255) : new Color(40, 42, 54);
        Color fields = isdarkmode ? new Color(255, 255, 255, 255) : new Color(40, 42, 54);
        Color backgroundcolor = isdarkmode ? new Color(40, 42, 54, 255) : new Color(255, 255, 255);

        getContentPane().setBackground(backgroundcolor);
        lbTotal.setForeground(labels);
        lbValorTotal.setForeground(labels);
        lbQTD.setForeground(labels);
        lbTrocoNum.setForeground(labels);
        lbTroco.setForeground(labels);
        lbQTD.setForeground(labels);
        lbPrecoReal.setForeground(labels);
        lbPreco.setForeground(labels);
        lbPrecoKgNum.setForeground(labels);
        lbPrecoKg.setForeground(labels);
        lbPrecoNum.setForeground(labels);
        lbNome.setForeground(labels);
        lbCodigo.setForeground(labels);
        lbPrecoKgReal.setForeground(labels);
        lbPrecoTotal.setForeground(labels);

        codigoField.setForeground(fields);
        valorPagoField.setForeground(fields);
        qtdField.setForeground(fields);
        codigoField.setBackground(backgroundcolor);
        valorPagoField.setBackground(backgroundcolor);
        qtdField.setBackground(backgroundcolor);


        areaProdutos.setBackground(backgroundcolor);
        areaProdutos.setForeground(fields);


    }

    private void toggle(ActionEvent actionEvent) {
        isdarkmode = !isdarkmode;
        ImageIcon iconesoloriginal = new ImageIcon("src/main/java/pdv/assets/sun-512.png");
        Image iconesolpegar = iconesoloriginal.getImage();
        Image iconesolredimensionada = iconesolpegar.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon iconesol = new ImageIcon(iconesolredimensionada);
        ImageIcon iconeluaoriginal = new ImageIcon("src/main/java/pdv/assets/lua.png");

        Image imagemlua = iconeluaoriginal.getImage();
        Image iconeluaredimensionada = imagemlua.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon iconelua = new ImageIcon(iconeluaredimensionada);

        modos.setIcon(isdarkmode ? iconesol : iconelua);
        trocardeCor();


    }

    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
    }

}
