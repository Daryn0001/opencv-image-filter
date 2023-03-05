package kz.dsalimov.src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kz.dsalimov.src.ui.EnterImagePage;
import nu.pattern.OpenCV;

public class Main extends Application {
    public static Stage window;

    public static void main(String[] args) {
        OpenCV.loadShared();
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        var enterImagePage = new EnterImagePage();

        enterImagePage.start();

        Scene scene = new Scene(enterImagePage.asParent(), 700, 500);
        window = new Stage();
        window.setTitle("CV Image Filter!");
        window.setScene(scene);
        window.show();
        window.requestFocus();
    }
}
