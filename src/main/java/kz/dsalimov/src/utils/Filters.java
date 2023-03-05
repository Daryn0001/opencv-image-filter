package kz.dsalimov.src.utils;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Filters {

    public Mat blur(Mat srcImg) {
        Mat filteredImd = new Mat();
        Imgproc.blur(srcImg, filteredImd, new Size(5, 5));

        return filteredImd;
    }

    public Mat boxFilter(Mat srcImg) {
        Mat filteredImd = new Mat();
        Imgproc.boxFilter(srcImg, filteredImd, -1, new Size(5, 5), new Point(-1, -1), true);

        return filteredImd;
    }

    public Mat dilate(Mat srcImg) {
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));

        Mat filteredImd = new Mat();

        Imgproc.dilate(srcImg, filteredImd, kernel);

        return filteredImd;
    }

    public Mat erode(Mat srcImg) {
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));

        Mat filteredImd = new Mat();

        Imgproc.erode(srcImg, filteredImd, kernel);

        return filteredImd;
    }

    public Mat pyrDown(Mat srcImg) {
        Mat filteredImd = new Mat();

        Imgproc.pyrDown(srcImg, filteredImd);

        System.out.println(" pyrDown: " + filteredImd.size());

        return filteredImd;
    }

    public Mat pyrUp(Mat srcImg) {
        Mat filteredImd = new Mat();

        Imgproc.pyrUp(srcImg, filteredImd);

        System.out.println(" pyrUp: " + filteredImd.size());


        return filteredImd;
    }

    public void showImage(Mat img, String title){
        CvUtilsFX.showImage(img, title);

    }
}
