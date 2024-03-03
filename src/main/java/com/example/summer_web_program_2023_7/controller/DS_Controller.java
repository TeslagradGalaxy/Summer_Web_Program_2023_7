package com.example.summer_web_program_2023_7.controller;
import com.example.summer_web_program_2023_7.fonction.ExtractFonction;
import com.example.summer_web_program_2023_7.mapper.ImageDataMapper;
import com.example.summer_web_program_2023_7.mapper.SaveMapper;
import com.example.summer_web_program_2023_7.wrapper.MatplotlibWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
@RestController
public class DS_Controller {
    @Autowired
    SaveMapper savemapper;
    @Autowired
    ImageDataMapper imageDataMapper;
    @PostMapping("/data_show")
    public void oins(@RequestParam("file") MultipartFile file) throws IOException {
        //调用自定义函数提取文件中有用数据
        ExtractFonction extractFonction=new ExtractFonction();
        extractFonction.extract_data(file);
        //sql操作，将处理后的文件中的数据导入数据库
        String filePath = "/usr/src/data.txt";
        savemapper.loadData(filePath);

        //python作图,以字节流的方式存入数据库中
        MatplotlibWrapper m = new MatplotlibWrapper();
        m.generatePlot();
    }
    @DeleteMapping("/data_show")
    public void ores(){
        savemapper.deleteData();
        imageDataMapper.deleteData();
        System.out.println("--数据已清除--");
    }
    @GetMapping("/pic_show")
    public void ohio(HttpServletResponse response) throws IOException {
            // 获取图像数据
            InputStream imageDataStream = imageDataMapper.getImageData();
            // 设置响应头信息
            response.setContentType("image/jpeg");
            // 将图像数据写入响应流
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];  // 缓冲区大小，可以根据实际情况调整
            int bytesRead;
            while ((bytesRead = imageDataStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            imageDataStream.close();
            outputStream.flush();
            outputStream.close();
        }
    }


