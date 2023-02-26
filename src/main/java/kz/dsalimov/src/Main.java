package kz.dsalimov.src;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main extends Application {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15.0);
        root.setAlignment(Pos.CENTER);

        Button button = new Button("Enter");
        button.setOnAction(this::onClickButton);
        root.getChildren().add(button);

        Scene scene = new Scene(root, 400, 150);
        stage.setScene(scene);
        stage.setTitle("OpenCV " + Core.VERSION);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
        });

        stage.show();
    }

    private void onClickButton(ActionEvent e) {
        Mat img = Imgcodecs.imread("C:\\Users\\Daryn\\Desktop\\opencv images\\moa3.png");
        if (img.empty()) {
            System.out.println(" image is empty");
            return;
        }
        CvUtilsFX.showImage(img, " MOAMETAL ");
        Mat img2 = new Mat();
        Imgproc.boxFilter(img, img2, -1, new Size(2, 2), new Point(-1, -1), false);
        CvUtilsFX.showImage(img2, " median 10 ");
        Mat img3 = new Mat();
        int d2 = 50;
        Imgproc.boxFilter(img, img3, -1, new Size(5, 5), new Point(-1, -1), true);
        CvUtilsFX.showImage(img3, " median 3 ");
        img.release();
        img2.release();
        img3.release();


    }
}