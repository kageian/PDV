package pdv.GUIs;

import com.fasterxml.jackson.databind.ObjectMapper;
import pdv.db.Produtos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProdutosCadastrados extends JFrame {
    private JTextField codigoField;
    private static final String CAMINHO_JSON = "produtos.json";

    private void Analisar() {
        String codigoProduto = codigoField.getText();
        File arquivo = new File(CAMINHO_JSON);
        try {
            ObjectMapper mapper = new ObjectMapper();
            Produtos[] produtosArray = mapper.readValue(arquivo, Produtos[].class);
            List<Produtos> produtos = new ArrayList<>(Arrays.asList(produtosArray));


            if (!codigoProduto.isEmpty()) {
                Produtos produtopararemover = null;
                for (Produtos p : produtos) {
                    if (codigoProduto.equals(p.getCode())) {
                        produtopararemover = p;
                        System.out.println("Produto Removido: "+produtopararemover);
                        break;
                    }
                }

                if (produtopararemover != null) {
                    produtos.remove(produtopararemover);
                    mapper.writeValue(arquivo, produtos);
                    System.out.println("Produto Removido");
                    dispose();
                    new ProdutosCadastrados();

                } else {
                    System.out.println("Produto não encontrado");
                }

            } else {
                System.out.println("Esta vazio");
            }

        } catch (Exception e) {
            System.out.println("Deu erro amigo...");
            ;
        }
    }

    public ProdutosCadastrados() {
        setTitle("Produtos Cadastrados");
        setSize(900, 455);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        String Caminho = "src/main/java/pdv/assets/";

        ImageIcon updateOriginal = new ImageIcon(Caminho + "update.png");
        Image updatepegar = updateOriginal.getImage();
        Image updateRedimensionar = updatepegar.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconeUpdate = new ImageIcon(updateRedimensionar);

        ImageIcon excluiroriginal = new ImageIcon(Caminho + "excluir.png");
        Image excluirpegar = excluiroriginal.getImage();
        Image excluirRedimensionar = excluirpegar.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconeexcluir = new ImageIcon(excluirRedimensionar);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBounds(300, 20, 570, 370);
        add(scroll);

        JLabel codigo = new JLabel("Codigo");
        codigo.setBounds(65, 10, 200, 100);
        codigo.setFont(new Font("Arial", Font.PLAIN, 50));

        JLabel lbExcluir = new JLabel("Excluir");
        lbExcluir.setBounds(23, 180, 100, 100);
        lbExcluir.setFont(new Font("Arial", Font.PLAIN, 25));

        JLabel lbUpdate = new JLabel("Atualizar");
        lbUpdate.setBounds(193, 180, 100, 100);
        lbUpdate.setFont(new Font("Arial", Font.PLAIN, 25));
        add(lbUpdate);
        add(lbExcluir);
        add(codigo);

        codigoField = new JTextField();
        codigoField.setBounds(0, 100, 300, 30);
        codigoField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(codigoField);

        JButton excluir = new JButton();
        excluir.setBounds(10, 250, 100, 100);
        excluir.setIcon(iconeexcluir);

        JButton update = new JButton();
        update.setBounds(190, 250, 100, 100);
        update.setIcon(iconeUpdate);

        add(update);
        add(excluir);

        excluir.addActionListener(this::ExcluirProduto);
        update.addActionListener(this::ExcluirProduto);


        try {
            ObjectMapper mapper = new ObjectMapper();
            File arquivo = new File(CAMINHO_JSON);

            if (arquivo.exists() && arquivo.length() > 0) {
                Produtos[] produtosArray = mapper.readValue(arquivo, Produtos[].class);
                List<Produtos> produtos = Arrays.asList(produtosArray);

                StringBuilder sb = new StringBuilder();
                for (Produtos p : produtos) {
                    sb.append("Código: ").append(p.getCode())
                            .append(" | Nome: ").append(p.getName())
                            .append(" | Preço: R$").append(String.format("%.2f", p.getPreco()))
                            .append(" | Preço/KG: R$").append(String.format("%.2f", p.getPrecokg()))
                            .append("\n");
                }

                areaTexto.setText(sb.toString());
            } else {
                areaTexto.setText("Nenhum produto cadastrado ainda.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + e.getMessage());
        }

        setVisible(true);
    }

    //Ações
    private void ExcluirProduto(ActionEvent actionEvent) {
        Analisar();
    }
}
