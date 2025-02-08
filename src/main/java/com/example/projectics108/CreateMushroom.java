package com.example.projectics108;

import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CreateMushroom {
    public int points = 0;
    private int[] scoreArray = new int[5];
    private File file;
    private PrintWriter output;
    private Scanner input;


    public void setMushroom(double timeNeeeded, ImageView imageView, int point, int count, int delay, Label score, Label topScores, Label gameOver, Button home) {
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        imageView.setX((int) (Math.random() * 720) + 40);
        imageView.setY(-60);

        if (count == 13) {
            imageView.setImage(null);
            Timeline animation = new Timeline();
            KeyValue keyValue = new KeyValue(imageView.yProperty(), 1);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(17), keyValue);
            animation.getKeyFrames().add(keyFrame);
            animation.setOnFinished(e -> {

                score.setText("Your Score: " + points);
                gameOver.setText("Game Over!");

                scoreArray = topScores(getTotalScore());
                topScores.setText(" Top Scores \n      " + scoreArray[0] + "\n      " + scoreArray[1] + "\n      " +
                        scoreArray[2]+ "\n      " + scoreArray[3] + "\n      " + scoreArray[4]);

                home.setVisible(true);

            });
            animation.play();
        } else {
            Timeline animation = new Timeline();
            KeyValue keyValue = new KeyValue(imageView.yProperty(), 860);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(timeNeeeded), keyValue);
            animation.getKeyFrames().add(keyFrame);
            animation.setDelay(Duration.millis(delay));
            clickImage(imageView, score, point);
            animation.play();}}

    public void clickImage(ImageView imageView, Label score, int point) {

        imageView.setOnMouseClicked(mouseEvent -> {
            points += point;
            imageView.setImage(null);
            score.setText("Score: " + points);
        });
    }

    public int getTotalScore() {
        return points;
    }

    public int[] topScores(int points) {
        file = new File("TopScores");
        try {
            input = new Scanner(file);
            int count = 0;
            while (input.hasNextInt()) {
                scoreArray[count] = input.nextInt();
                count++;
            }


            for (int i = 0; i < scoreArray.length; i++) {
                if (points > scoreArray[i]) {
                    int temp = scoreArray[i];
                    scoreArray[i] = points;
                    points = temp;
                }

            }
            output = new PrintWriter(file);
            for (int j = 0; j < scoreArray.length; j++)
                output.println(scoreArray[j]);

            output.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return scoreArray;
    }


}
