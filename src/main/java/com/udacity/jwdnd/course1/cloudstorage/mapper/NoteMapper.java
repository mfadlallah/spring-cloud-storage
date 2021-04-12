package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNoteByNoteId(int noteId);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> getNotesByUserId(int userid);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note getNotesById(int noteId);

    @Select("SELECT noteid FROM NOTES WHERE notetitle = #{noteTitle}")
    Integer getNoteIdByTitle(String noteTitle);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES(#{noteTitle}, #{noteDescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription} WHERE noteid = #{noteId}")
    int edit(Note note);
}
