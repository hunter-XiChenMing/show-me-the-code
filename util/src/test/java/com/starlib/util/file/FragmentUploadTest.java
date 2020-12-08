package com.starlib.util.file;

import com.starlib.model.file.PartFile;
import org.junit.Test;
import java.io.IOException;
import java.util.UUID;

public class FragmentUploadTest {
    @Test
    public void MergeFile() {
        PartFile[] files = new PartFile[3];
        files[0] = new PartFile("C:\\Users\\86183\\Desktop\\1.txt",1);
        files[1] = new PartFile("C:\\Users\\86183\\Desktop\\2.txt",2);
        files[2] = new PartFile("C:\\Users\\86183\\Desktop\\3.txt",3);
        FragmentUpload.mergeFile(files,"C:\\Users\\86183\\Desktop\\"+ UUID.randomUUID().toString()+".txt");
    }
}