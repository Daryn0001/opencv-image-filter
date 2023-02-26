package kz.dsalimov.src;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


public class CvUtilsFX {

    public static WritableImage matToWritableImage(Mat mat) throws IOException {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, matOfByte);

        byte[] byteArray = matOfByte.toArray();

        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage bufImage = ImageIO.read(in);
        System.out.println(" Image loaded");
        return SwingFXUtils.toFXImage(bufImage, null);
    }


    public static WritableImage matToImageFX(Mat mat) {
        if (mat == null || mat.empty()) return null;
        if (mat.depth() == CvType.CV_8U) {
        } else if (mat.depth() == CvType.CV_16U) {
            Mat m_16 = new Mat();
            mat.convertTo(m_16, CvType.CV_8U, 255.0 / 65535);
            mat = m_16;
        } else if (mat.depth() == CvType.CV_32F) {
            Mat m_32 = new Mat();
            mat.convertTo(m_32, CvType.CV_8U, 255);
            mat = m_32;
        } else return null;

        if (mat.channels() == 1) {
            Mat m_bgra = new Mat();
            Imgproc.cvtColor(mat, m_bgra, Imgproc.COLOR_GRAY2BGRA);
            mat = m_bgra;
        } else if(mat.channels() == 3) {
            Mat m_bgra = new Mat();
            Imgproc.cvtColor(mat, m_bgra, Imgproc.COLOR_BGR2BGRA);
            mat = m_bgra;
        }
        else if (mat.channels() == 4) {
        } else return null;


        byte[] buf = new byte[mat.channels() * mat.cols() * mat.rows()];
        mat.get(0, 0, buf);

        WritableImage wim = new WritableImage(mat.cols(), mat.rows());
        PixelWriter pw = wim.getPixelWriter();
        pw.setPixels(0, 0, mat.cols(), mat.rows(),
                WritablePixelFormat.getByteBgraInstance(),
                buf, 0, mat.cols() * 4);

        return wim;
    }

    public static Mat imageFXToMat(Image img) {
        return null;
    }

    public static void showImage(Mat img, String title) {
        Image im = matToImageFX(img);

        Stage window = new Stage();
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
        window.setScene(scene);
        window.setTitle(title);
        window.show();
    }


}
