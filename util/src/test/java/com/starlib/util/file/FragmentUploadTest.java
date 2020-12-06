package com.starlib.util.file;

import com.starlib.model.file.PartFile;
import org.junit.Test;
import java.io.IOException;

public class FragmentUploadTest {
    @Test
    public void MergeFile() throws IOException {
        PartFile[] files = new PartFile[3];
        files[0] = new PartFile("C:\\Users\\86183\\Desktop\\1.txt");
        files[1] = new PartFile("C:\\Users\\86183\\Desktop\\2.txt");
        files[2] = new PartFile("C:\\Users\\86183\\Desktop\\3.txt");
        FragmentUpload.mergeFile(files,"C:\\Users\\86183\\Desktop\\merge.txt");
    }
}