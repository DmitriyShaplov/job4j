package ru.job4j.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author shaplov
 * @since 28.06.2019
 */
@WebServlet("/AjaxUploadFileServlet")
@MultipartConfig
public class AjaxUploadFileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadImagePath = applicationPath + File.separator + "images";
        String uploadFilePath = applicationPath + File.separator + "uploads";
        File fileDir = new File(uploadFilePath);
        File imageDir = new File(uploadImagePath);
        if (!fileDir.exists()) {
            var res = fileDir.mkdirs();
        }
        String base64Image = "";
        String fileName = null;
        for (Part part : req.getParts()) {
            fileName = part.getSubmittedFileName();
            part.write(imageDir + File.separator + fileName);
            try {
                Files.copy(Paths.get(imageDir + File.separator + fileName), Paths.get(fileDir + File.separator + fileName));
            } catch (IOException e) {
                System.out.println(e);
            }
            File image = new File(imageDir + File.separator + fileName);
            byte[] imageData = Files.readAllBytes(image.toPath());
            base64Image = Base64.getEncoder().encodeToString(imageData);
        }
        PrintWriter out = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("fileName", fileName);
        if (fileName != null && fileName.contains("jpg")) {
            objectNode.put("image", fileName);
            objectNode.put("image64", base64Image);
        }
        out.append(objectMapper.writeValueAsString(objectNode));
        out.flush();
    }
}
