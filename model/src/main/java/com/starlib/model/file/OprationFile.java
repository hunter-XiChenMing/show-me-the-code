package com.starlib.model.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 文件内容操作类
 */
public class OprationFile extends RandomAccessFile {
    private File file;
    private final Object closeLock;
    volatile boolean closed;

    public OprationFile(String path, String mode) throws FileNotFoundException {
        this(new File(path), mode);
    }

    public OprationFile(File file, String mode) throws FileNotFoundException {
        super(file, mode);
        this.file = file;
        this.closeLock = new Object();
    }

    @Override
    public void close() throws IOException {
        super.close();
        synchronized (this.closeLock) {
            if (this.closed) {
                return;
            }
            this.closed = true;
        }
    }
}
