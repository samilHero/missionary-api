package com.samill.missionary_backend.common.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Converter
@Slf4j
public class AesEncryptConverter implements AttributeConverter<String, String> {
    public static String ALG = "AES/CBC/PKCS5Padding";

    @Value("${aes.encrypt.key}")
    private String KEY;

    @Override
    public String convertToDatabaseColumn(String s) {
        String iv = KEY.substring(0, 16); // 16byte
        try{
            Cipher cipher = Cipher.getInstance(ALG);
            SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(s.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (InvalidAlgorithmParameterException e) {
            log.error("invalid or inappropriate algorithm parameters");
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            log.error("a particular padding mechanism is requested but is not available in the environment.");
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            log.error("the length of data provided to a block cipher is incorrect");
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            log.error("The Character Encoding is not supported.");
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            log.error("a particular cryptographic algorithm is requested but is not available in the environment.");
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            log.error("a particular padding mechanism is expected for the input data but the data is not padded properly.");
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            log.error("invalid Keys");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String s) {
        String iv = KEY.substring(0, 16); // 16byte
        try{
            Cipher cipher = Cipher.getInstance(ALG);
            SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(s);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");
        } catch (InvalidAlgorithmParameterException e) {
            log.error("invalid or inappropriate algorithm parameters");
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            log.error("a particular padding mechanism is requested but is not available in the environment.");
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            log.error("the length of data provided to a block cipher is incorrect");
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            log.error("The Character Encoding is not supported.");
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            log.error("a particular cryptographic algorithm is requested but is not available in the environment.");
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            log.error("a particular padding mechanism is expected for the input data but the data is not padded properly.");
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            log.error("invalid Keys");
            throw new RuntimeException(e);
        }

    }
}
