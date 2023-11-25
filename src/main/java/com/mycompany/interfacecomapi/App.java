package com.mycompany.interfacecomapi;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class App extends Application {

    private ImageView imageView;
    private Label geraLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GERA RANDOM");

        Text titulo = new Text("Bem-vindo à GERA RANDOM!");
        titulo.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));

        String textoLoren = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                "Nulla metus justo, placerat et placerat maximus, hendrerit quis sem.";
        Text labelTexto = new Text(textoLoren);
        labelTexto.setWrappingWidth(400);

        Label labelTexto2 = new Label(textoLoren);
        Button botaoPopup = new Button("Clique aqui");
        Button botaoGerar = new Button("Gerar Random");

        Label subtituloLabel = new Label("Seção Importante");
        subtituloLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        Label textoLabel = new Label("Pellentesque euismod nisi eu congue viverra. In hac habitasse platea dictumst. Praesent sodales nunc id faucibus ultricies. Duis non sagittis sapien. In sit amet mauris sed turpis fermentum venenatis. Nulla facilisi. Maecenas lobortis, sem sit amet tincidunt aliquam, leo felis tempus tortor, ac semper ipsum lectus vitae sem. Proin eros diam, tempus vehicula condimentum commodo, placerat sed ligula.");
        textoLabel.setWrapText(true);
        textoLabel.setMaxWidth(400);
        textoLabel.setMaxHeight(150);

        Image imagem = new Image(new File("C:\\Users\\vinic\\Documents\\NetBeansProjects\\InterfaceComAPI\\horizontal.png").toURI().toString());
        imageView = new ImageView(imagem);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        Label subtituloTextoAleatorio = new Label("Clique para gerar random:");
        subtituloTextoAleatorio.setFont(Font.font("Times New Roman", FontWeight.BOLD, 22));

        geraLabel = new Label();

        botaoPopup.setOnAction(e -> exibirPopup("Alerta!"));
        botaoGerar.setOnAction(e -> obterConselho());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(
                titulo, labelTexto, botaoPopup, subtituloLabel, textoLabel,
                imageView, subtituloTextoAleatorio, botaoGerar, geraLabel);

        Scene cena = new Scene(layout, 400, 500);
        primaryStage.setScene(cena);

        primaryStage.show();
    }

    private void obterConselho() {
        String resultadoAPI = chamarAPI("https://randomuser.me/api/");
        geraLabel.setText(resultadoAPI);
    }

    private String chamarAPI(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                StringBuilder resposta = new StringBuilder();
                while (scanner.hasNextLine()) {
                    resposta.append(scanner.nextLine());
                }
                return resposta.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro de API";
        }
    }

    private void exibirPopup(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Popup");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        alert.showAndWait();
    }
}
