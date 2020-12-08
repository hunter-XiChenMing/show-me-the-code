package com.starlib.util.file;

import com.starlib.model.file.OprationFile;
import com.starlib.model.file.PartFile;
import com.starlib.util.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class FragmentUpload {

    private static Logger LOGGER = LoggerFactory.getLogger(FragmentUpload.class);

    /**
     * @param files 合并分片文件
     */
    public static void mergeFile(PartFile[] files, String path) {
        OprationFile writeFile = null;
        try {
            writeFile = new OprationFile(path, "rw");
            long position = 0;
            for (PartFile file : files) {
                try (OprationFile readFile = new OprationFile(file.getFilePath(), "rw")) {
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
                    LOGGER.info("file part"+file.getFileIndex()+" successful merged.");
                }
            }
        } catch (IOException e){
            LOGGER.error(e.getMessage());
        } finally{
            if (writeFile != null) {
                try{
                    writeFile.close();
                } catch (IOException e){
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }

}
