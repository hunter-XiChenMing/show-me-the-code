package com.starlib.model.file;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PartFile {
    private int fileIndex;
    private String filePath;

    public PartFile(String filePath, int index) {
        this.filePath = filePath;
        this.fileIndex = index;
    }
}
