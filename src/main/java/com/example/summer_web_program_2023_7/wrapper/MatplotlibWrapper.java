package com.example.summer_web_program_2023_7.wrapper;
import java.io.*;

public class MatplotlibWrapper {

    public static void generatePlot() throws IOException {

        Process proc;

        proc = Runtime.getRuntime().exec("/usr/server/Python-3.10.11/python /usr/server/main.py");// 执行py文件
        //用输入输出流来截取结果,前一个路径为python启动器的位置，后一个路径为脚本位置"D:\\ProgramData\\python\\python.exe D:\\ProgramData\\python_data\\main.py "
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();


    }
}