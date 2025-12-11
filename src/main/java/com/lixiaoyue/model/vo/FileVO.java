package com.lixiaoyue.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileVO implements Serializable {
    String path;
    String fileName;
    String fileType;
    String fileSize;

    public String getFileSize(){
        return fileSize+"KB";
    }
}
