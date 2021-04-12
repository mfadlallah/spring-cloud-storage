package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialsService {

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private EncryptionService encryptionService;

    public List<Credential> getCredentialsByUserId(int userId) {
        return credentialMapper.getCredentialsByUserId(userId);
    }

    public String store(String url, String userName, String password, int userId) {
        String encodedSalt = getSalt();
        String hashedPassword = encryptionService.encryptValue(password, encodedSalt);

        Credential credential = new Credential(
                url,
                userName,
                hashedPassword,
                encodedSalt,
                userId
        );

        credentialMapper.insert(credential);
        return encodedSalt;
    }

    public String editCredential(int credentialId, String url, String userName, String password) {

        String encodedSalt = getSalt();
        String hashedPassword = encryptionService.encryptValue(password, encodedSalt);

        Credential newCredential = new Credential(
                credentialId,
                url,
                userName,
                hashedPassword,
                encodedSalt
        );

        credentialMapper.edit(newCredential);
        return encodedSalt;
    }

    private String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String getPassword(String encryptedPassword, String key) {
        if (key.isEmpty()) return "";
        return encryptionService.decryptValue(encryptedPassword, key);
    }

    public void deleteCredential(int credentialId, int userId) {
        credentialMapper.deleteCredentialByCredentialId(credentialId, userId);
    }
}
