package com.example.summer_web_program_2023_7.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.io.InputStream;

@Mapper
public interface ImageDataMapper {
    @Select("SELECT image FROM image_data")
    InputStream getImageData();
    @Delete("delete from image_data")
    void deleteData();
}