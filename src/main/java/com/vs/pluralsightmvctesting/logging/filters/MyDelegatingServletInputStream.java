package com.vs.pluralsightmvctesting.logging.filters;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDelegatingServletInputStream extends ServletInputStream {

    private final InputStream sourceStream;

    public MyDelegatingServletInputStream(InputStream sourceStream) {
        this.sourceStream = sourceStream;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }



    @Override
    public int read() throws IOException {
        return 0;
    }
}
