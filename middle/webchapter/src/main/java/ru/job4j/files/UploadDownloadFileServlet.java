package ru.job4j.files;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * @author shaplov
 * @since 27.06.2019
 */
@WebServlet("/UploadDownloadFileServlet")
@MultipartConfig
public class UploadDownloadFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fileName = req.getParameter("fileName");
        if (fileName == null || fileName.equals("")) {
            throw new ServletException("File name can't be null or empty");
        }
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + "uploads";
        File file = new File(uploadFilePath + File.separator + fileName);
        if (!file.exists()) {
            throw new ServletException("File doesn't exists");
        }
        try (InputStream fis = new FileInputStream(file)) {
            ServletContext context = getServletContext();
            String mimeType = context.getMimeType(file.getAbsolutePath());
            resp.setContentType(mimeType != null ? mimeType : "application/octet-stream");
            resp.setContentLength((int) file.length());
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            try (ServletOutputStream os = resp.getOutputStream()) {
                byte[] buffer = new byte[8096];
                int read = 0;
                while ((read = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<html><head></head><body>");
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + "uploads";
        File fileDir = new File(uploadFilePath);
        if (!fileDir.exists()) {
            var res = fileDir.mkdirs();
        }
        String fileName = null;
        for (Part part : req.getParts()) {
            fileName = part.getSubmittedFileName();
            File file = new File(fileDir + File.separator + fileName);
            System.out.println(file.getAbsolutePath());
            part.write(fileDir + File.separator + fileName);
            out.write("File " + fileName + " uploaded successfully.");
            out.write("<br>");
            out.write("<a href=\"UploadDownloadFileServlet?fileName=" + fileName + "\">Download " + fileName + "</a>");
        }
        out.write("</body></html>");
    }
}
