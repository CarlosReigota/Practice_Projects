import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.security.SecureRandom;

public class Gerador extends Application {

    // Constantes para caracteres
    private static final String minusculo = "abcdefghijklmnopqrstuvwxyz";
    private static final String maiusculo = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String numeros = "0123456789";
    private static final String caracter_especial = "!@#$%^&*()-_+=<>?";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerador de Senha Aleatória");

        // Criando os componentes da interface gráfica
        Label lengthLabel = new Label("Comprimento da senha (15-20 caracteres):");
        Slider lengthSlider = new Slider(15, 20, 15);
        lengthSlider.setBlockIncrement(1);
        lengthSlider.setShowTickLabels(true);
        lengthSlider.setShowTickMarks(true);

        CheckBox includeLowercase = new CheckBox("Incluir letras minúsculas");
        includeLowercase.setSelected(true);
        CheckBox includeUppercase = new CheckBox("Incluir letras maiúsculas");
        includeUppercase.setSelected(true);
        CheckBox includeDigits = new CheckBox("Incluir números");
        includeDigits.setSelected(true);
        CheckBox includeSpecialChars = new CheckBox("Incluir caracteres especiais");
        includeSpecialChars.setSelected(true);

        Button generateButton = new Button("Gerar Senha");
        TextField passwordField = new TextField();
        passwordField.setEditable(false);

        // Ação ao clicar no botão
        generateButton.setOnAction(e -> {
            int length = (int) lengthSlider.getValue();
            boolean hasLower = includeLowercase.isSelected();
            boolean hasUpper = includeUppercase.isSelected();
            boolean hasDigits = includeDigits.isSelected();
            boolean hasSpecial = includeSpecialChars.isSelected();

            String password = generatePassword(length, hasLower, hasUpper, hasDigits, hasSpecial);
            passwordField.setText(password);
        });

        // Layout
        VBox vbox = new VBox(10, lengthLabel, lengthSlider, includeLowercase, includeUppercase, includeDigits, includeSpecialChars, generateButton, passwordField);
        vbox.setStyle("-fx-padding: 20px;");

        // Cena e exibição
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para gerar a senha
    private String generatePassword(int length, boolean includeLower, boolean includeUpper, boolean includeDigits, boolean includeSpecial) {
        StringBuilder allowedChars = new StringBuilder();
        if (includeLower) allowedChars.append(minusculo);
        if (includeUpper) allowedChars.append(maiusculo);
        if (includeDigits) allowedChars.append(numeros);
        if (includeSpecial) allowedChars.append(caracter_especial);

        if (allowedChars.length() == 0) {
            throw new IllegalArgumentException("Pelo menos uma categoria de caracteres deve ser selecionada.");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Garantir que a senha tenha ao menos um de cada tipo selecionado
        if (includeLower) password.append(minusculo.charAt(random.nextInt(minusculo.length())));
        if (includeUpper) password.append(maiusculo.charAt(random.nextInt(maiusculo.length())));
        if (includeDigits) password.append(numeros.charAt(random.nextInt(numeros.length())));
        if (includeSpecial) password.append(caracter_especial.charAt(random.nextInt(caracter_especial.length())));

        // Preencher o restante da senha com caracteres aleatórios
        for (int i = password.length(); i < length; i++) {
            password.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }

        // Embaralhar a senha para garantir aleatoriedade
        return shuffleString(password.toString());
    }

    // Método para embaralhar a senha gerada
    private String shuffleString(String input) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            int randomIndex = random.nextInt(sb.length());
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(randomIndex));
            sb.setCharAt(randomIndex, temp);
        }
        return sb.toString();
    }
}

