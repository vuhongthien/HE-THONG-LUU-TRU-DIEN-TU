//package vn.fis.logfile.vinasoy.controller;
//
//import com.fis.logfile.model.Attachment;
//import com.fis.logfile.service.AttachmentServiceImpl;
//import com.fis.logfile.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import vn.fis.logfile.vinasoy.config.security.UserDetailsServiceImpl;
//import vn.fis.logfile.vinasoy.model.entity.Attachment;
//import vn.fis.logfile.vinasoy.service.impl.AttachmentServiceImpl;
//
//import javax.activation.MimetypesFileTypeMap;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//
//public class AttachmentController {
//    @Autowired
//    private AttachmentServiceImpl attachmentService;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//    private InputStream inputStream = null;
//    private OutputStream outputStream = null;
//
//    private Attachment attachment = null;
//
//    Long idUser;
//
//
//
//    @RequestMapping("/")
//    public String viewList(Model model, Principal principal) {
//        String username = principal.getName();
//        this.idUser = userDetailsService.getIdUser();
//        String pathServer = "F:" + File.separator + "FIS"; // đường dẫn gốc F:/FIS
//        model.addAttribute("listAttachments", getList(pathServer));
//        return "index";
//    }
//
//
//    public List<Attachment> getList(String path) {
//        File f = new File(path); // folder = file mà có đường dẫn = path
//        File[] filesList = f.listFiles(); // lấy tất cả file nằm trong folder
//        List<Attachment> listFileServer = new ArrayList<>();
//        for (int i = 0; i < filesList.length; i++) {
//            attachment = new Attachment();
//            String fileNameInFolder = filesList[i].getName(); // lấy tên file ở server
//            //attachment = attachmentService.getByNameInFolder(fileNameInFolder); // lấy ra các thuộc tính của file dựa theo name
//            attachment = attachmentService.getByNameInFolder(fileNameInFolder); // lấy ra các thuộc tính của file dựa theo name
//            Long idUserHost = 2L;
//            if (this.idUser == 2) {
//                listFileServer.add(attachment);
//                continue;
//            }
//            if (attachment.getIdEmployee() == this.idUser || attachment.getIdEmployee() == idUserHost) {
//                listFileServer.add(attachment);
//            } else {
//                if (attachmentService.checkIsShareForUser(attachment.getId(), this.idUser) != null) {
//                    listFileServer.add(attachment);
//                }
//            }
//
//
//        }
//        return listFileServer;
//    }
//
//    @RequestMapping(value = {"/listAttachment/{idFolder}"}, method = RequestMethod.GET)
//    // nếu typeOfFile là folder thì mở cái này
//    protected String viewListFolder(@PathVariable Long idFolder, Model model) throws ServletException, IOException {
//        String path = attachmentService.getById(idFolder).getFilePathServer();
//        model.addAttribute("listAttachments", getList(path));
//        return "listAttachment";
//    }
//
//
//    @RequestMapping(value = {"/fileList/{id}"}, method = RequestMethod.GET)
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp, @PathVariable(required = false) Long id) throws ServletException, IOException {
//        inputStream = null;
//        outputStream = null;
//
//        attachment = new Attachment();
//        attachment = attachmentService.getById(id);
//        String mineType = attachment.getTypeOfFile();
//
//        try {
////            String dir = "F://USER";
////            if (BeanName.KEDB.equalsIgnoreCase(type)) {
////                dir = FileUtils.KEDB_ATTACHMENT_DIRECTORY + File.separator + id;
////            } else /* if (BeanName.TICKET.equalsIgnoreCase(type)) */ {
////                dir = FileUtils.SMS_ATTACHMENTS + File.separator + attachment.getAttachObjectType() + File.separator
////                        + attachment.getAttachObjectId() + File.separator + id;
////            }
//            String attachmentPath = attachment.getFilePathServer();
//            inputStream = Files.newInputStream(Paths.get(attachmentPath));
//            // IOUtils jar used to convert Input Stream to byte array easily
//
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//
//            int len;
//
//            // read bytes from the input stream and store them in the buffer
//            while ((len = inputStream.read(buffer)) != -1) {
//                // write bytes from the buffer into the output stream
//                os.write(buffer, 0, len);
//            }
//            byte[] bytes = os.toByteArray();
//
//            MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
//            String _MimetypesFileTypeMap = "";
//            switch (mineType) {
//                case ".pdf":
//                    _MimetypesFileTypeMap = "application/pdf";
//                    break;
//                case ".jpg":
//                    _MimetypesFileTypeMap = "image/jpg";
//                    break;
//                case ".txt":
//                    _MimetypesFileTypeMap = "text/plain";
//                    break;
//                default:
//                    _MimetypesFileTypeMap = "application/octet-stream";
//                    break;
//            }
//
//            if (new File(attachmentPath).exists()) {
//                resp.setContentType(_MimetypesFileTypeMap + "; charset=UTF-8");
//                resp.setCharacterEncoding("UTF-8");
//                resp.setHeader("X-Frame-Options", "SAMEORIGIN");
//                if (req.getHeader("User-Agent").indexOf("IE") != -1 || req.getHeader("User-Agent").indexOf("Chrome") != -1) {
//                    resp.setHeader("Content-disposition", " inline; filename=" + URLEncoder.encode(attachment.getFileName(), StandardCharsets.UTF_8.toString()).replace("+", "%20"));
//                } else {
//                    resp.setHeader("Content-disposition", " inline; filename*=UTF-8''" + URLEncoder.encode(attachment.getFileName(), StandardCharsets.UTF_8.toString()).replace("+", "%20"));
//                }
//
//                outputStream = resp.getOutputStream();
//                outputStream.write(bytes);
////                resp.getOutputStream().write(Files.readAllBytes(Paths.get(attachment.getFileName())));
//            } else
//                resp.sendError(404, "File not found");
//        } catch (Exception e) {
//            resp.sendError(404, e.toString());
//        } finally {
//            if (inputStream != null)
//                inputStream.close();
//            if (outputStream != null)
//                outputStream.close();
//        }
//    }
//
//
//    @RequestMapping(value = {"/sendAttachments"}, method = RequestMethod.GET)
//    protected String send(HttpServletRequest req, HttpServletResponse resp, @RequestParam("id") List<Long> listIdSend, Model model) {
//        model.addAttribute("listAttachments", attachmentService.getAttachmentSend(listIdSend));
//        return "listAttachment";
//    }
//
//
//}
