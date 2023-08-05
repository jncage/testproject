import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
    private static final float EPSILON = 1e-6f;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Task2 <circleFile> <pointsFile>");
            return;
        }

        try (BufferedReader circleReader = new BufferedReader(new FileReader(args[0]));
             BufferedReader pointsReader = new BufferedReader(new FileReader(args[1]))) {

            float centerX, centerY, radius;
            String[] circleCoordinates = circleReader.readLine().split(" ");
            centerX = Float.parseFloat(circleCoordinates[0]);
            centerY = Float.parseFloat(circleCoordinates[1]);
            radius = Float.parseFloat(circleReader.readLine());

            String line;
            while ((line = pointsReader.readLine()) != null) {
                String[] pointCoordinates = line.split(" ");
                float x = Float.parseFloat(pointCoordinates[0]);
                float y = Float.parseFloat(pointCoordinates[1]);
                int position = getPointPosition(centerX, centerY, radius, x, y);
                System.out.print(position + " ");
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getPointPosition(float centerX, float centerY, float radius, float x, float y) {
        float squaredDistance = (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY);

        if (Math.abs(squaredDistance - radius * radius) <= EPSILON * EPSILON) {
            return 0;
        } else if (squaredDistance < radius * radius) {
            return 1;
        } else {
            return 2;
        }
    }
}
