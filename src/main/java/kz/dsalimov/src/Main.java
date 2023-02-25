package kz.dsalimov.src;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

public class Main {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("C:\\Users\\Daryn\\Desktop\\opencv images\\moametal.jpg");
        if (img.empty()) {
            System.out.println(" Empty ");
            return;
        }
        System.out.println("img.width: " + img.width());
        System.out.println("img.height: " + img.height());
        System.out.println("img.type: " + CvType.typeToString(img.type()));
        System.out.println("img.channels: " + img.channels());
        CvUtilsFX ll = new CvUtilsFX();
        var saved = Imgcodecs.imwrite(
                "C:\\Users\\Daryn\\Desktop\\opencv images\\moametal_test.jpg",
                img,
                new MatOfInt(Imgcodecs.IMWRITE_PAM_FORMAT_GRAYSCALE, 100)
        );
        if (saved) {
            System.out.println("Saved successfully");
        } else {
            System.out.println("Failed to save");
        }
//        Imgcodecs.imwrite("C:\\Users\\Daryn\\Desktop\\opencv images", img);


    }
}