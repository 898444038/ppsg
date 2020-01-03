package com.ming.system.controller;

import com.ming.system.utils.ResultMsg;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RequestMapping("/upload")
@Controller
public class UploadController {

    @Value("${upload.image.realpath}")
    private String uploadImagePath;//图片绝对路径
    @Value("${upload.file.realpath}")
    private String uploadFilePath;//文件绝对路径

    @Value("${upload.image.relativepath}")
    private String uploadImageRelativePath;//图片相对路径
    @Value("${upload.file.relativepath}")
    private String uploadFileRelativePath;//文件相对路径

    @RequestMapping("download/{path}/{ext}")
    public void downloadFileAction(@PathVariable String path, @PathVariable String ext, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            File file = new File(uploadFilePath+path);
            String fileName = file.getName();
            fileName = fileName.substring(fileName.lastIndexOf("_")+1);
            //fileName = fileName.substring(0,fileName.lastIndexOf("."));
            if(file.exists()){
                fis = new FileInputStream(file);
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                IOUtils.copy(fis, response.getOutputStream());
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/file")
    @ResponseBody
    public Map<String,Object> uploadFile(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        //创建map集合
        List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        try {
            //转换成多部分request
            MultipartHttpServletRequest multipartRequest=((MultipartHttpServletRequest) request);
            //获得上传的所有文件名
            Iterator<String> fileNameIter = multipartRequest.getFileNames();
            //进行循环遍历
            while (fileNameIter.hasNext()){
                map=new HashMap<>();
                //根据文件名获取文件
                MultipartFile file =multipartRequest.getFile(fileNameIter.next());
                //若文件不为null
                if(file!=null){
                    //获取上传时的文件名
                    String myFilename=file.getOriginalFilename();
                    //System.out.println("上传时的文件名:"+myFilename);
                    if(myFilename!=null){
                        myFilename = myFilename.trim();
                    }
                    //去除空格
                    if(!"".equals(myFilename)){
                        //获得文件名
                        String filename=file.getOriginalFilename()==null?new Random().nextInt(10000)+"":file.getOriginalFilename();
                        //截取文件名
                        //String fileBaseName=filename.substring(0,filename.lastIndexOf("."));

                        //截取文件前缀
                        String fileFront=filename.substring(0,filename.lastIndexOf(".")).toLowerCase();
                        //截取文件后缀
                        String fileExt=filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
                        //生成新的文件名
                        String filenames = fileFront+"_"+System.currentTimeMillis()+"";
                        //获得保存文件路径
                        //String filePath=request.getSession().getServletContext().getRealPath("/upload");

                        String contextPath = request.getContextPath();//项目名

                        String projectPath = uploadFilePath+filenames+"."+fileExt;
                        //System.out.println("保存的路径:"+projectPath);
                        //map.put("realpath",projectPath);
                        File targetFile = new File(projectPath);
                        if(!targetFile.exists()){
                            //先得到文件的上级目录，并创建上级目录，在创建文件
                            targetFile.getParentFile().mkdir();
                            try {
                                //创建文件
                                targetFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        //上传文件
                        file.transferTo(targetFile);
                        //获得工程的相对路径(InterceptorConfig修改)
                        //String saveUrl = contextPath+uploadFileRelativePath+"/"+filenames+"."+fileExt;
                        String saveUrl = filenames+"."+fileExt;
                        //将文件保存的相对路径返回页面
                        map.put("path",saveUrl);
                        map.put("ext",fileExt);
                        mapList.add(map);
                    }
                }else{
                    result.put("success",0);// 0 表示上传失败，1 表示上传成功
                    result.put("message","上传失败");
                    result.put("url","");
                }
            }
            //保存添加信息
            result.put("success",1);// 0 表示上传失败，1 表示上传成功
            result.put("message","上传成功");
            result.put("url",mapList);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",0);// 0 表示上传失败，1 表示上传成功
            result.put("message","上传失败");
            result.put("url","");
        }
        return result;
    }

    @RequestMapping("/image")
    @ResponseBody
    public ResultMsg uploadImage(HttpServletRequest request){
        //Map<String,Object> result = new HashMap<>();

        //创建map集合
        Map<String,Object> map=new HashMap<>();
        try {
            //转换成多部分request
            MultipartHttpServletRequest multipartRequest=((MultipartHttpServletRequest) request);
            //获得上传的所有文件名
            Iterator<String> fileNameIter = multipartRequest.getFileNames();
            //进行循环遍历
            while (fileNameIter.hasNext()){
                //根据文件名获取文件
                MultipartFile file =multipartRequest.getFile(fileNameIter.next());
                //若文件不为null
                if(file!=null){
                    //获取上传时的文件名
                    String myFilename=file.getOriginalFilename();
                    //System.out.println("上传时的文件名:"+myFilename);
                    if(myFilename!=null){
                        myFilename = myFilename.trim();
                    }
                    //去除空格
                    if(!"".equals(myFilename)){
                        //获得文件名
                        String filename=file.getOriginalFilename()==null?new Random().nextInt(10000)+"":file.getOriginalFilename();
                        //截取文件名
                        //String fileBaseName=filename.substring(0,filename.lastIndexOf("."));
                        //截取文件后缀
                        String fileExt=filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
                        //生成新的文件名
                        String filenames = System.currentTimeMillis()+"";
                        //获得保存文件路径
                        //String filePath=request.getSession().getServletContext().getRealPath("/upload");

                        //System.out.println(request.getSession().getServletContext().getRealPath("/upload"));
                        //System.out.println(request.getSession().getServletContext().getRealPath("/uploadFile"));

                        //String scheme = request.getScheme();//http
                        //String serverName = request.getServerName();//localhost
                        //int serverPort = request.getServerPort();//8080
                        String contextPath = request.getContextPath();//项目名
                        //String url = scheme+"://"+serverName+":"+serverPort+contextPath;//http://127.0.0.1:8088/ming

                        //保存文件"E:/ming/src/main/resources/static/upload/"
                        //String projectPath = filePath+filenames+".png";

                        String projectPath = uploadImagePath+filenames+"."+fileExt;
                        //System.out.println("保存的路径:"+projectPath);
                        //map.put("realpath",projectPath);
                        File targetFile = new File(projectPath);
                        if(!targetFile.exists()){
                            //先得到文件的上级目录，并创建上级目录，在创建文件
                            targetFile.getParentFile().mkdir();
                            try {
                                //创建文件
                                targetFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        //上传文件
                        file.transferTo(targetFile);
                        //获得工程的相对路径(InterceptorConfig修改)
                        String saveUrl = contextPath+uploadImageRelativePath+"/"+filenames+"."+fileExt;
                        //将文件保存的相对路径返回页面
                        map.put("path",saveUrl);
                    }
                }else{
                    return ResultMsg.failed("上传失败");//上传失败
                }
            }
            //保存添加信息
            return ResultMsg.success("上传成功",map.get("path"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.failed("上传失败");//上传失败
        }
    }

    /**
     * 按宽度值等比例缩放
     * @param srcImageFile
     * @param tmpwidth
     * @param flag
     */
    /*public final static void scaleWithWidth(String srcImageFile, int tmpwidth, boolean flag) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长
            float scale =width/tmpwidth;
            int ewidth=width;
            int eheight=height;
            if (flag) {// 放大
                ewidth = (int)(width * scale);
                eheight =(int)(height * scale);
            } else {// 缩小
                if(width>tmpwidth){
                    ewidth = (int)(width / scale);
                    eheight = (int)(height / scale);
                }
            }
            Image image = src.getScaledInstance(ewidth, eheight, Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(ewidth, eheight, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "JPEG", new File(srcImageFile));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}