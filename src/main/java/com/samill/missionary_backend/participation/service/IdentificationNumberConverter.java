package com.samill.missionary_backend.participation.service;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Converter
@RequiredArgsConstructor
public class IdentificationNumberConverter implements AttributeConverter<String, String> {
    private final AesBytesEncryptor encryptor;

    @Override
    public String convertToDatabaseColumn(String s) {
        return encrypt(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return decrypt(s);
    }

    public String encrypt(String text) {
        byte[] encrypt = encryptor.encrypt(text.getBytes(StandardCharsets.UTF_8));
        return byteArrayToString(encrypt);
    }

    public String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte abyte :bytes){
            sb.append(abyte);
            sb.append(" ");
        }
        return sb.toString();
    }

    public String decrypt(String text) {
        byte[] decryptBytes = stringToByteArray(text);
        byte[] decrypt = encryptor.decrypt(decryptBytes);
        return new String(decrypt, StandardCharsets.UTF_8);
    }

    public byte[] stringToByteArray(String byteString) {
        String[] split = byteString.split("\\s");
        ByteBuffer buffer = ByteBuffer.allocate(split.length);
        for (String s : split) {
            buffer.put((byte) Integer.parseInt(s));
        }
        return buffer.array();
    }
}
