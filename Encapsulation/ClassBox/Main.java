package ClassBox;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int length = Integer.parseInt(scan.nextLine());
        int width = Integer.parseInt(scan.nextLine());
        int height = Integer.parseInt(scan.nextLine());
        try{
            Box box = new Box(length,width,height);

            System.out.printf("Surface Area - %.2f%n",box.calculateSurfaceArea());
            System.out.printf("Lateral Surface Area - %.2f%n",box.calculateLateralSurfaceArea());
            System.out.printf("Volume – %.2f%n",box.calculateVolume());

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }
    }
}
