package ru.job4j.iotasks;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shaplov
 * @since 18.03.2019
 * @version 1
 */
public class InStreamService {

    /**
     * Checks is there an even number in input stream.
     */
    public boolean isNumber(InputStream in) throws IOException {
        try (InputStream inStream = in) {
            boolean numbers = true;
            int lastByte = -1;
            int c;
            while ((c = inStream.read()) != -1) {
                if (c < 48 || c > 57) {
                    numbers = false;
                    break;
                }
                lastByte = c;
            }
            return lastByte % 2 == 0 && numbers;
        }
    }



    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            Set<String> abuseSet = Arrays.stream(abuse).collect(Collectors.toSet());
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    out.write(System.lineSeparator().getBytes());
                }
                List<String> list = Arrays.stream(line.split(" "))
                        .filter(v -> !abuseSet.contains(v)).collect(Collectors.toList());
                for (int i = 0; i < list.size(); i++) {
                    out.write(list.get(i).getBytes());
                    if (i < list.size() - 1) {
                        out.write(32);
                    }
                }
            }
        }
    }
}
