package com.codecool.klondike;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class Klondike extends Application {

    private static final double WINDOW_WIDTH = 1400;
    private static final double WINDOW_HEIGHT = 900;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Card.loadCardImages("card_images/card_back.png");
        Game game = new Game();
        game.setTableBackground(new Image("/table/green.png"));

        addMenu(primaryStage, game);

        primaryStage.setTitle("Klondike Solitaire");
        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
    }

    private void addMenu(Stage primaryStage, Game game) {

        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Options");

        MenuItem restart = new MenuItem("Restart");
        MenuItem exit = new MenuItem("Exit");

        menuBar.getMenus().addAll(gameMenu);
        menuBar.setStyle("fx-padding: 1  5 1 5");
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        gameMenu.getItems().add(restart);
        gameMenu.getItems().add(exit);

        game.getChildren().add(menuBar);

        restart.setOnAction((event) -> {
            start(primaryStage);
        });

        exit.setOnAction((event) -> {
            System.exit(0);
        });
    }


}
