package com.example.projectics108;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MushroomKingdom extends Application {
    private final Image[] images = {new Image("RedMushroom.png"),
            new Image("OneUP.png"),
            new Image("PoisonMushroom.png")
    };
    private int timeNeeded = 13;
    private ImageView imageView;


    @Override
    public void start(Stage stage) throws Exception {

        Group rootH1 = new Group();
        HBox hBox = new HBox();
        hBox.setSpacing(200);

        Label redScore = new Label("+5 POINTS");
        redScore.setTranslateX(80);
        redScore.setTranslateY(660);
        redScore.setFont(Font.loadFont(new FileInputStream(new File("src/main/resources/slkscr.ttf")), 20));
        redScore.setTextFill(Color.web("#F7E064"));


        Label greenScore = new Label("+10 POINTS");
        greenScore.setTranslateX(340);
        greenScore.setTranslateY(660);
        greenScore.setFont(Font.loadFont(new FileInputStream(new File("src/main/resources/slkscr.ttf")), 20));
        greenScore.setTextFill(Color.web("#F7E064"));

        Label purplScore = new Label("-5 POINTS");
        purplScore.setTranslateX(610);
        purplScore.setTranslateY(660);
        purplScore.setFont(Font.loadFont(new FileInputStream(new File("src/main/resources/slkscr.ttf")), 20));
        purplScore.setTextFill(Color.web("#F7E064"));


        for (int i = 0; i < images.length; i++) {
            imageView = new ImageView(images[i]);
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);
            hBox.getChildren().add(imageView);

        }

        hBox.setTranslateX(105);
        hBox.setTranslateY(590);
        rootH1.getChildren().addAll(hBox, redScore, greenScore, purplScore);
        Image homeBG = new Image("HomeBackGround.png", 800, 800, true, true);

        Scene sceneH1 = new Scene(rootH1, homeBG.getHeight(), homeBG.getWidth());
        sceneH1.setFill(new ImagePattern(homeBG));

        Button btn = new Button();
        btn.setTranslateX(325);
        btn.setTranslateY(450);

        btn.setFont(Font.loadFont(new FileInputStream(new File("src/main/resources/slkscr.ttf")), 20));
        btn.setTextFill(Color.web("#FAF9F6"));
        btn.setStyle("-fx-border-color: black; -fx-background-color: #F1CC04;");
        btn.setText("START GAME");


        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Group root = new Group();
                Font fontSize1 = null;
                Font fontSize2 = null;
                Font fontSize3 = null;


                try {
                    fontSize1 = Font.loadFont(new FileInputStream(new File("src/main/resources/slkscr.ttf")), 20);


                    fontSize2 = Font.loadFont(new FileInputStream(new File("src/main/resources/slkscr.ttf")), 30);


                    fontSize3 = Font.loadFont(new FileInputStream(new File("src/main/resources/slkscr.ttf")), 60);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                Label score = new Label("Score: 0");
                score.setLayoutX(0);
                score.setLayoutY(0);
                score.setFont(fontSize1);


                Label topScores = new Label();
                topScores.setTranslateX(270);
                topScores.setTranslateY(250);
                topScores.setFont(fontSize2);


                Label gameOver = new Label();
                gameOver.setTranslateX(200);
                gameOver.setTranslateY(150);
                gameOver.setFont(fontSize3);
                gameOver.setTextFill(Color.web("#F1CC04"));


                Button home = new Button("Home");
                home.setTranslateX(335);
                home.setTranslateY(500);
                home.setFont(fontSize1);
                home.setTextFill(Color.web("#FAF9F6"));
                home.setStyle("-fx-border-color: black; -fx-background-color: #F1CC04;");
                home.setVisible(false);
                home.setOnMouseClicked(a -> {
                    stage.setScene(sceneH1);
                    timeNeeded = 13;
                });


                root.getChildren().addAll(score, topScores, gameOver, home);
                CreateMushroom mushroom = new CreateMushroom();


                int delay;
                for (int i = 0; i <= 12; i++) {

                    if (i == 5 || i == 7)
                        delay = 6000;
                    else if (i > 9) {
                        delay = 13000;

                    } else
                        delay = (int) (Math.random() * 3000 + i * 1000);

                    imageView = new ImageView(images[0]);
                    mushroom.setMushroom(timeNeeded, imageView, 5, i + 1, delay, score, topScores, gameOver, home);
                    root.getChildren().add(imageView);
                    timeNeeded -= 0.5;
                }

                for (int i = 0; i < 4; i++) {
                    delay = (int) (Math.random() * 5000 + i * 3000);
                    imageView = new ImageView(images[1]);
                    mushroom.setMushroom(2, imageView, 10, 1, delay, score, topScores, gameOver, home);
                    root.getChildren().add(imageView);
                }


                for (int i = 0; i < 6; i++) {
                    delay = (int) (Math.random() * 2000 + i * 1500);
                    imageView = new ImageView(images[2]);
                    mushroom.setMushroom(7, imageView, -5, 1, delay, score, topScores, gameOver, home);
                    root.getChildren().add(imageView);
                }
                Image background = new Image("MarioBackground.jpeg", 800, 800, true, true);


                Scene scene = new Scene(root, background.getWidth(), background.getHeight());
                scene.setFill(new ImagePattern(background));
                stage.setScene(scene);

            }
        });
        rootH1.getChildren().add(btn);


        stage.setScene(sceneH1);
        stage.setTitle("Mushroom Kingdom");
        stage.show();


    }
}
