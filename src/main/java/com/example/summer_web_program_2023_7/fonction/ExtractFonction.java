package com.example.summer_web_program_2023_7.fonction;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractFonction {
    public void extract_data(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            System.out.println("文件为空！");
        } else {
            try {
                String fileName = file.getOriginalFilename();
                File newFile = new File("/usr/src/" + fileName);
                file.transferTo(newFile);

                // 处理文件上传成功的逻辑

                //从传入文件中拾取有用的信息
                String inputFile = newFile.getPath();
                String outputFile = "/usr/src/data.txt";

                try (BufferedReader br = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8));
                     BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8))) {

                    String line;
                    while ((line = br.readLine()) != null) {
                        Pattern pattern = Pattern.compile("时间=(\\d+)ms");
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            String time = matcher.group(1);
                            bw.write(time);
                            bw.newLine();
                        } else {
                            System.err.println("无法提取时间数据: " + line);
                        }
                    }

                    System.out.println("提取完成，数据已保存到 " + outputFile);
                } catch (IOException e) {
                    System.err.println("无法打开文件！");
                }


            } catch (IOException e) {
                // 处理文件上传失败的情况
                System.out.println("文件上传失败！");
            }
        }
    }
}