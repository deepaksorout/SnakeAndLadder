package com.example.snake_and_ladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import javax.security.auth.x500.X500Principal;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class PlayAreaController {
    HashMap<Pair<Double,Double>,Pair<Double,Double>> map=new HashMap<>();
    @FXML
    Text number;
    @FXML
    Text change;
    int turn=1;
    @FXML
    ImageView player1;
    @FXML
    ImageView player2;

    @FXML
    public void roll(MouseEvent event)throws IOException{
        setpair();
        Random random=new Random();
        int rolling=random.nextInt(6)+1;
        // rolling =1;
        number.setText(String.valueOf(rolling));

        if(turn==1){
            Pair<Double,Double> moveCoordinates=movement(player1.getTranslateX(),player1.getTranslateY(),rolling);
            player1.setTranslateX(moveCoordinates.getKey());
            player1.setTranslateY(moveCoordinates.getValue());
            if(map.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue()))){
                player1.setTranslateX(map.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player1.setTranslateY(map.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkwin(player1.getTranslateX(),player1.getTranslateY());
        }
        else{
            Pair<Double,Double> moveCoordinates=movement(player2.getTranslateX(),player2.getTranslateY(),rolling);
            player2.setTranslateX(moveCoordinates.getKey());
            player2.setTranslateY(moveCoordinates.getValue());
            if(map.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue()))){
                player2.setTranslateX(map.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player2.setTranslateY(map.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkwin(player2.getTranslateX(),player2.getTranslateY());
        }

        if(turn==1 && rolling!=6){
            turn=2;
            change.setText("Player 2's turn");
        }
        else if(rolling!=6){
            turn=1;
            change.setText("Player 1's turn");
        }
    }
    Pair<Double, Double> movement(double x,double y,int rolling){
        Double movex= x;
        Double movey= y;
        if(movey%100==0){
            movex+=rolling*50;
            if(movex>500){
                movex=500*2-movex+50;
                movey-=50;
            }
        }
        else{
            movex-=rolling*50;
            if(movex<50){
                if(y==-450){
                    return new Pair<>(x,y);
                }
                movex= -1*(movex-50);
                movey-=50;
            }
        }
        return new Pair<>(movex,movey);
    }
    void checkwin(double x,double y) throws IOException {
        if(x==50 && y==-450){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Result");
            if(turn==1){
                alert.setContentText("Player 1 has Won");;
                alert.showAndWait();
            }
            else{
                alert.setContentText("Player 2 has Won");
                alert.showAndWait();
            }
            GamePage page =new GamePage();
            HelloApplication.root.getChildren().setAll(page.root);
        }
    }
    void setpair(){
        map.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));
        map.put(new Pair<>(200.0,0.0),new Pair<>(350.0,-50.0));
        map.put(new Pair<>(450.0,0.0),new Pair<>(500.0,-150.0));
        map.put(new Pair<>(50.0,-100.0),new Pair<>(100.0,-200.0));
        map.put(new Pair<>(400.0,-100.0),new Pair<>(200.0,-400.0));
        map.put(new Pair<>(500.0,-250.0),new Pair<>(350.0,-300.0));
        map.put(new Pair<>(500.0,-350.0),new Pair<>(500.0,-450.0));
        map.put(new Pair<>(50.0,-350.0),new Pair<>(50.0,-450.0));
        map.put(new Pair<>(200.0,-50.0),new Pair<>(350.0,0.0));
        map.put(new Pair<>(350.0,-250.0),new Pair<>(350.0,-150.0));
        map.put(new Pair<>(100.0,-300.0),new Pair<>(100.0,-50.0));
        map.put(new Pair<>(200.0,-300.0),new Pair<>(50.0,-250.0));
        map.put(new Pair<>(350.0,-400.0),new Pair<>(200.0,-100.0));
        map.put(new Pair<>(400.0,-450.0),new Pair<>(400.0,-350.0));
        map.put(new Pair<>(300.0,-450.0),new Pair<>(300.0,-350.0));
        map.put(new Pair<>(150.0,-450.0),new Pair<>(100.0,-350.0));
    }
}
