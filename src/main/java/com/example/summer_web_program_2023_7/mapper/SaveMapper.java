package com.example.summer_web_program_2023_7.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface SaveMapper {
    @Update("LOAD DATA INFILE #{filePath} INTO TABLE time_data")
    void loadData(@Param("filePath") String filePath);

    @Delete("delete from time_data")
    void deleteData();

}
