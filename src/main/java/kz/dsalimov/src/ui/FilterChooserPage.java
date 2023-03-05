package kz.dsalimov.src.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kz.dsalimov.src.utils.Filters;
import org.opencv.core.Mat;

import java.net.URL;

import static kz.dsalimov.src.utils.CvUtilsFX.matToImageFX;

public class FilterChooserPage {
    private final Filters filters;
    private final Stage window = new Stage();

    public static Mat img;

    public Parent root;

    @FXML
    Button enterOrig;
    @FXML
    Button enterBlur;
    @FXML
    Button enterBoxFilter;
    @FXML
    Button enterDilate;
    @FXML
    Button enterErode;
    @FXML
    Button enterPyrDown;
    @FXML
    Button enterPyrUp;


    public FilterChooserPage() {
        this.filters = new Filters();
    }

    public Parent asParent() {
        return root;
    }

    public void onEnterOrig() {
        showImage(img, "ORIGINAL");
    }

    public void onEnterBlur() {
        var filtered = this.filters.blur(img);
        showImage(filtered, " BLUR ");

        release(filtered);
    }

    public void onEnterBoxFilter() {
        var filtered = this.filters.boxFilter(img);
        showImage(filtered, " BoxFilter ");

        release(filtered);
    }

    public void onEnterDilate() {
        var filtered = this.filters.dilate(img);
        showImage(filtered, " Dilate ");

        release(filtered);
    }

    public void onEnterErode() {
        var filtered = this.filters.erode(img);
        showImage(filtered, " Erode ");

        release(filtered);
    }

    public void onEnterPyrDown() {
        var filtered = this.filters.pyrDown(img);
        showImage(filtered, " PyrDown ");

        release(filtered);
    }

    public void onEnterPyrUp() {
        var filtered = this.filters.pyrUp(img);
        showImage(filtered, " PyrUp ");

        release(filtered);
    }

    public void showImage(Mat img, String title) {
        Image im = matToImageFX(img);

        ScrollPane sp = new ScrollPane();
        ImageView iv = new ImageView();

        if (im != null) {
            iv.setImage(im);
            if (im.getWidth() < 1900) {
                sp.setPrefWidth(im.getWidth() + 5);
            } else sp.setPrefWidth(1900.0);

            if (im.getHeight() < 1000) {
                sp.setPrefHeight(im.getHeight() + 5);
            } else sp.setPrefHeight(1000.0);
        }
        sp.setContent(iv);
        sp.setPannable(true);

        BorderPane box = new BorderPane();
        box.setCenter(sp);

        Scene scene = new Scene(box);

        if (window.isShowing()) {
            window.close();
        }

        window.setScene(scene);
        window.setTitle(title);
        window.setX(-1);
        window.show();
    }

    public void release(Mat img) {
        img.release();
    }

    public void start() {
        URL fxmlLocation = FilterChooserPage.class.getResource("filter-chooser-page.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);


        try {
            root = loader.load();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
