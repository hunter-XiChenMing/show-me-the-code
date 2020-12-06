package com.starlib.util.file;

import com.starlib.model.file.OprationFile;
import com.starlib.model.file.PartFile;
import com.starlib.util.constant.Constant;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FragmentUpload {
    /**
     * @param files 合并分片文件
     */
    public static void mergeFile(PartFile[] files, String path) throws IOException {
        OprationFile writeFile = null;
        long position = 0;
        try {
            for (PartFile file : files) {
                try (OprationFile readFile = new OprationFile(file.getFilePath(), "rw")) {
                    writeFile = new OprationFile(path, "rw");
                    int chunkSize = Constant.INT_10240;
                    byte[] buf = new byte[chunkSize];
                    int byteCount = readFile.read(buf);
                    while (byteCount != -1) {
                        if (byteCount != chunkSize) {
                            byte[] tempByte = new byte[byteCount];
                            System.arraycopy(buf, 0, tempByte, 0, byteCount);
                            buf = tempByte;
                        }
                        writeFile.seek(position);
                        writeFile.write(buf);
                        position += byteCount;
                        byteCount = readFile.read(buf);
                    }
                }
            }
        } finally {
            if (writeFile != null) {
                writeFile.close();
            }
        }
    }

}
