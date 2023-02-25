package kz.dsalimov.src;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class CvUtilsFX extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.getChildren().addAll(new ArcPane(), new LinePane(), new CirclePane());

        Scene scene = new Scene(pane, 400, 350);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hang man");
        primaryStage.show();


    }
}

class CirclePane extends Pane {
    public CirclePane() {
        Circle circle = new Circle();
        circle.setCenterX(210);
        circle.setCenterY(90);
        circle.setRadius(30);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        getChildren().add(circle);
    }
}

class LinePane extends Pane {
    public LinePane() {
        Line line1 = new Line(80, 20, 80, 290);
        Line line2 = new Line(80, 20, 210, 20);
        Line line3 = new Line(210, 20, 210, 60);
        Line line4 = new Line(210, 120, 210, 220);

        Line line5 = new Line(210, 220, 140, 280);
        Line line6 = new Line(210, 220, 280, 280);

        Line line7 = new Line(195, 105, 105, 185);
        Line line8 = new Line(225, 105, 315, 185);

        getChildren().addAll(line1, line2, line3, line4, line5, line6, line7, line8);

    }
}

class ArcPane extends Pane {
    public ArcPane() {
        Arc arc1 = new Arc(80, 330, 60, 40, 0, 180);
        arc1.setFill(Color.WHITE);
        arc1.setStrokeWidth(3);
        arc1.setStroke(Color.BLACK);
        arc1.setType(ArcType.OPEN);
        getChildren().add(arc1);

    }
}
