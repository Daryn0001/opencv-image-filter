package kz.dsalimov.src.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import kz.dsalimov.src.Main;
import kz.dsalimov.src.utils.Filters;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.net.URL;


public class EnterImagePage {
    Parent root;

    public Parent asParent() {
        return root;
    }

    @FXML
    Button enterButton;

    @FXML
    protected void onEnterButton() {
        String path = chooseFile();
        Mat img = Imgcodecs.imread(path);
        if (img.empty()) {
            System.out.println(" image is empty");
            return;
        }

        Filters filters = new Filters();
        filters.showImage(img, " MOAMETAL ");


        var filterChooserPage = new FilterChooserPage();
        filterChooserPage.start();
        FilterChooserPage.img = img;

        Main.window.setScene(new Scene(filterChooserPage.asParent(), 500, 500));
        Main.window.setTitle(" Filters ");

    }

    private String chooseFile() {
        FileChooser chooser = new FileChooser();
        return chooser.showOpenDialog(null).getAbsolutePath();
    }

    public void start() {
        URL fxmlLocation = EnterImagePage.class.getResource("enter-image-page.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);

        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
