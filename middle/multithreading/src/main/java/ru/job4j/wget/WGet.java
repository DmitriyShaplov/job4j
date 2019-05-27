package ru.job4j.wget;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author shaplov
 * @since 25.05.2019
 */
public class WGet {

    /**
     * Flag indicating whether we can download now.
     */
    private volatile boolean permission = true;

    private volatile boolean reset = true;

    public WGet() {
    }

    public void reset() {
        reset = true;
    }

    /**
     * Download file with limited speed.
     * @param link link on file.
     * @param limitStr number of limit in kilobytes.
     * @throws IOException possible Exception.
     * @throws InterruptedException possible Exception.
     */
    public void download(String link, String limitStr) throws IOException, InterruptedException {
        int limit = Integer.parseInt(limitStr) * 1024;
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName;
            String disposition = conn.getHeaderField("Content-Disposition");
            if (disposition != null && disposition.contains("filename=")) {
                fileName = disposition.substring(disposition.indexOf("filename="));
            } else {
                fileName = link.substring(link.lastIndexOf("/") + 1);
            }
            try (InputStream input = conn.getInputStream();
                 FileOutputStream out = new FileOutputStream(fileName)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                int bytesReadInSec = 0;
                while ((bytesRead = input.read(buffer)) != -1) {
                    if (reset) {
                        bytesReadInSec = 0;
                        reset = false;
                    }
                    out.write(buffer, 0, bytesRead);
                    bytesReadInSec += bytesRead;
                    if (bytesRead == buffer.length && bytesReadInSec >= limit) {
                        synchronized (this) {
                            permission = false;
                            while (!permission) {
                                wait();
                            }
                            bytesReadInSec = 0;
                        }
                    }
                }
            }
        }
        conn.disconnect();
    }

    public void checkPermission() {
        if (!permission) {
            synchronized (this) {
                permission = true;
                notify();
            }
        }
    }
}
