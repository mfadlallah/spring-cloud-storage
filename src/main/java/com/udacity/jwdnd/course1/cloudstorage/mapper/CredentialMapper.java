package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    void deleteCredentialByCredentialId(int credentialid);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credential> getCredentialsByUserId(int userid);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    Credential getCredentialsById(int credentialid);

    @Select("SELECT credentialid FROM CREDENTIALS WHERE username = #{username}")
    Integer getCredentialsByUsername(String username);

    @Insert("INSERT INTO CREDENTIALS (username, url, key, userid, password) " +
            "VALUES(#{username}, #{url}, #{key}, #{userid}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insert(Credential credential);

    @Update("UPDATE CREDENTIALS SET username=#{username}, url=#{url}, key=#{key}, password=#{password} WHERE credentialid = #{credentialid}")
    int edit(Credential credential);
}
