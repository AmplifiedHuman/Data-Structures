package projectCode20280.exercises;

import projectCode20280.ChainHashMap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CountWordOccurrence {
    public static void main(String[] args) {
        ChainHashMap<String, Integer> map = new ChainHashMap<>();
        try (Scanner in = new Scanner(new File("src/projectCode20280/resources/sample_text.txt"))) {
            while (in.hasNext()) {
                String next = in.next();
                next = next.replaceAll(",", "");
                if (!next.isEmpty()) {
                    Integer tempValue = map.get(next);
                    if (tempValue == null) {
                        map.put(next, 1);
                    } else {
                        map.put(next, tempValue + 1);
                    }
                }
            }
            System.out.println(map);
        } catch (IOException ex) {
            System.out.println("IO error occurred");
            ex.printStackTrace();
        }
    }
}
