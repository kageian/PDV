package pdv.GUIs;

import com.fasterxml.jackson.databind.ObjectMapper;
import pdv.db.Produtos;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class cadastrarProduto extends JFrame {
    private JTextField nomeField, codigoField, precoField, precoKgField;
    private static final String CAMINHO_JSON = "produtos.json";
    private List<Produtos> listaProdutos = new ArrayList<>();

    public cadastrarProduto() {
        setSize(550, 455);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        Font fontePadrao = new Font("Arial", Font.PLAIN, 30);

        // Labels
        JLabel titulo = new JLabel("Cadastro de produtos");
        titulo.setBounds(60, 5, 450, 100);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));

        JLabel nome = new JLabel("Nome do Produto");
        nome.setBounds(50, 110, 300, 100);
        nome.setFont(fontePadrao);

        JLabel codigo = new JLabel("Código");
        codigo.setBounds(50, 185, 100, 100);
        codigo.setFont(fontePadrao);

        JLabel preco = new JLabel("Preço");
        preco.setBounds(50, 260, 100, 100);
        preco.setFont(fontePadrao);

        JLabel precoKg = new JLabel("Preço por KG");
        precoKg.setBounds(50, 330, 200, 100);
        precoKg.setFont(fontePadrao);

        add(precoKg);
        add(nome);
        add(preco);
        add(codigo);
        add(titulo);

        // Fields
        nomeField = new JTextField();
        nomeField.setBounds(300, 145, 200, 35);
        nomeField.setFont(fontePadrao);

        codigoField = new JTextField();
        codigoField.setBounds(300, 215, 200, 35);
        codigoField.setFont(fontePadrao);

        precoField = new JTextField();
        precoField.setBounds(300, 290, 200, 35);
        precoField.setFont(fontePadrao);

        precoKgField = new JTextField();
        precoKgField.setBounds(300, 360, 200, 35);
        precoKgField.setFont(fontePadrao);

        add(precoKgField);
        add(precoField);
        add(nomeField);
        add(codigoField);

        precoKgField.addActionListener(e -> {
            cadastrar();
        });
        precoField.addActionListener(e -> {
            cadastrar();
        });
        nomeField.addActionListener(e -> {
            cadastrar();
        });
        codigoField.addActionListener(e -> {
            cadastrar();
        });
    }


    private void cadastrar() {
        try {
            String codigo = codigoField.getText();
            String nome = nomeField.getText();
            String precokgText = precoKgField.getText();
            String precoText = precoField.getText();

            if (codigo.isEmpty() || nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os Campos devem ser preenchidos");
                return;
            }
            double preco;
            double precokg;

            if (precoText.isEmpty()) {
                preco = 0;
            } else {
                preco = Double.parseDouble(precoField.getText().trim().replace(",", "."));
            }

            if (precokgText.isEmpty()) {
                precokg = 0;
            } else {
                precokg = Double.parseDouble(precoKgField.getText().replace(",", ".").trim());
            }


            Produtos novoProduto = new Produtos();
            novoProduto.setPrecokg(precokg);
            novoProduto.setPreco(preco);
            novoProduto.setName(nome);
            novoProduto.setCode(codigo);


            // Ajuda a ler e escrever arquivos json e transformar em json também
            ObjectMapper mapper = new ObjectMapper();

            // Cria uma referencia do arquivo produtos.json, não cria nem nada, apenas o arquivo é o caminho para o produtos.json
            File arquivo = new File(CAMINHO_JSON);

            // Uma condição caso o arquvio existir e tiver algo dentro dele ele irá ler a lista dos produtos e colocar dentro de uma lista chamda listaProdutos.
            if (arquivo.exists() && arquivo.length() > 0) {
                // Le o arquivo json e converte em um array de Produtos
                Produtos[] produtosAntigos = mapper.readValue(arquivo, Produtos[].class);
                // Converte o array que acabmos de converter em uma lista mutável para adicionarmos, removermos e vermos o que há dentro
                listaProdutos = new ArrayList<>(List.of(produtosAntigos));
                System.out.println("Lista ja criada, e produtos cadastrados tbm.");
                // Caso não exista arquvio nenhum e se o arquvio estiver vazio ele criara uma lista nova para que possa ser colocados novos produtos dentro.
            } else {
                listaProdutos = new ArrayList<>();
                System.out.println("Criou uma lista para colocar novos produtos");
                System.out.println();
            }
            // Verifica se já existe produto com mesmo nome ou código
            // Produtos é os produtos ja cadastrados na minha lista e novoProduto foi o que eu digitei
            boolean produtoExiste = listaProdutos.stream().anyMatch(produtos ->
                    produtos.getCode().equalsIgnoreCase(novoProduto.getCode()) ||
                            produtos.getName().equalsIgnoreCase(novoProduto.getName())
            );


            if (produtoExiste) {
                JOptionPane.showMessageDialog(this, "Já existe um produto com esse nome ou código!");
                return; // sai do método e não adiciona o produto
            } else {
                listaProdutos.add(novoProduto);
            }


            mapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, listaProdutos);


            nomeField.setText("");
            codigoField.setText("");
            precoField.setText("");
            precoKgField.setText("");

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
        }
    }


}


