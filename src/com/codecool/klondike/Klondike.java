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
    private static String cardBack = "card_images/card_back.png";
    private static String backgroundPic = "/table/green.png";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Card.loadCardImages(cardBack);
        Game game = new Game();
        game.setTableBackground(new Image(backgroundPic));

        addMenu(primaryStage, game);

        primaryStage.setTitle("Klondike Solitaire");
        primaryStage.setScene(new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT));

        primaryStage.show();
    }

    private void addMenu(Stage primaryStage, Game game) {

        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Options");
        Menu themeMenu = new Menu("Theme");


        MenuItem restart = new MenuItem("Restart");
        MenuItem exit = new MenuItem("Exit");

        MenuItem theme1 = new MenuItem("Space Stone");

        menuBar.getMenus().addAll(gameMenu, themeMenu);
        menuBar.setStyle("fx-padding: 1  5 1 5");
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        gameMenu.getItems().add(restart);
        gameMenu.getItems().add(exit);
        themeMenu.getItems().add(theme1);

        game.getChildren().add(menuBar);

        restart.setOnAction((event) -> {
            start(primaryStage);
        });

        exit.setOnAction((event) -> {
            System.exit(0);
        });

        theme1.setOnAction((event) -> {
            cardBack = "/theme/cb1.png";
            backgroundPic = "/theme/bg1.png";
            start(primaryStage);
        });


    }


}
