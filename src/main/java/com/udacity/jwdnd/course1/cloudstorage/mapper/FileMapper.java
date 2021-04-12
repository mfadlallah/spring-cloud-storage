package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId} AND userid = #{userId}")
    void deleteFileByFileId(int fileId, int userId);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getFilesByUserId(int userid);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFilesById(int fileId);

    @Select("SELECT fileId FROM FILES WHERE filename = #{filename}")
    Integer getFilesByName(String filename);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);
}
