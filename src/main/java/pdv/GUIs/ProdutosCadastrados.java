package pdv.GUIs;

import com.fasterxml.jackson.databind.ObjectMapper;
import pdv.db.Produtos;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProdutosCadastrados extends JFrame {
    private static final String CAMINHO_JSON = "produtos.json";

    public ProdutosCadastrados() {
        setTitle("Produtos Cadastrados");
        setSize(630, 455);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBounds(20, 20, 570, 370);
        add(scroll);

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
}
