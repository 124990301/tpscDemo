package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.List;

/**
 * Created by xuyf on 2018/10/11.
 */
@Controller
public class TpscController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/tpsc")
    public String tpsc(MultipartFile tp, User user, String name) {
        System.out.println(user.getName() + "----" + user.getXb());
        System.out.println(user.getTx());
        System.out.println("name:" + name);
//        for(MultipartFile file:tp){
        try {
            //判断文件目录是否存在，否则自动生成
            File directory = new File("d:/tpsc");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // 转存文件
            tp.transferTo(new File("d:/tpsc/" + tp.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }

        return "result";
    }

    @RequestMapping("/tpscAjax")
    @ResponseBody
    public String tpscAjax(MultipartFile tp, User user, String name) {
        System.out.println(user.getName() + "----" + user.getXb());
        System.out.println(user.getTx());
        System.out.println("name:" + name);
//        for(MultipartFile file:tp){
        try {
            //判断文件目录是否存在，否则自动生成
            File directory = new File("d:/tpsc");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // 转存文件
            tp.transferTo(new File("d:/tpsc/" + tp.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
        return "result";
    }

    @RequestMapping("/sjk")
    @ResponseBody
    public String sjkTest() {
        String sql = "select count(*) from xt_ysxx";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("数据总数：" + count);
        return count.toString();
    }

    @GetMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
            File file = new File("D:/wjxz.txt");

            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + "aaaa.txt");// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    }

    @RequestMapping("/updateSqwj")
    @ResponseBody
    public void updateSqwj(@RequestBody JSONObject json) throws IOException {
        String type = json.getString("type");
        String jgbh = json.getString("jgbh");
        String data = JSONObject.toJSONString(json.getJSONObject("data"));
        //文件目录
        Path path = Paths.get("D:/"+jgbh+".wadata");
        Sqwj sqwj = null;
        if(Files.notExists(path)){  //新增
            Files.createFile(path);
            sqwj = new Sqwj();
        }else{  //修改
            String sqwjJson =  new String (Files.readAllBytes(path),"UTF-8");
            sqwj = JSONObject.parseObject(sqwjJson,Sqwj.class);
        }
        sqwj.updateSqwj(type,data);
        Files.write(path, JSONObject.toJSONString(sqwj).getBytes("UTF-8"));
//        //data.js是文件
//        Path path = rootLocation.resolve("data.js");
//        byte[] strToBytes = jsonStr.getBytes();
//        Files.write(path, strToBytes);
    }

    @RequestMapping("/wjjm")
    @ResponseBody
    public String wjjm(@RequestBody JSONObject json){
        String mac = json.getString("mac");
        String jgbh = json.getString("jgbh");

        return null;
    }
}