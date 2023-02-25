FROM maven:3.8.7-openjdk-18
WORKDIR .
COPY target/opencv-image-filter-1.0.jar .

CMD ["java", "-jar", "opencv-image-filter-1.0.jar"]