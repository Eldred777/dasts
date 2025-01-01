package com.github.eldred777.dasts;

import com.github.eldred777.dasts.stats.relics.Relics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 640, 480);

        root.getChildren().add(new Relics().view());

        stage.setScene(scene);
        stage.setTitle("DASTS");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
