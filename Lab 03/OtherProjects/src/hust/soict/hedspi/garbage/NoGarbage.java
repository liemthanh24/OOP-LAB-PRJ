package hust.soict.hedspi.garbage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) throws Exception {
        String filename = "C:\\Users\\Admin\\Downloads\\Tong_hop_Ngu_phap_N4_N3_day_du.docx";
        byte[] inputBytes = Files.readAllBytes(Paths.get(filename));

        long startTime1 = System.currentTimeMillis();
        StringBuffer outputStringBuffer = new StringBuffer();

        for (byte b : inputBytes) {
            outputStringBuffer.append((char) b);
        }

        long endTime1 = System.currentTimeMillis();
        System.out.println("Time with StringBuffer: " + (endTime1 - startTime1) + "ms");

        long startTime2 = System.currentTimeMillis();
        StringBuilder outputStringBuilder = new StringBuilder();
        for (byte b : inputBytes) {
            outputStringBuilder.append((char) b);
        }
        String outputString = outputStringBuilder.toString();
        long endTime2 = System.currentTimeMillis();
        System.out.println("Time with StringBuilder: " + (endTime2 - startTime2) + "ms");
    }
}