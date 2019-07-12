package com.venscor.dbcp;

import javax.tools.*;
import javax.tools.JavaFileObject.Kind;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;

class MemoryJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
    final Map<String, byte[]> classBytes = new HashMap();

    MemoryJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    public Map<String, byte[]> getClassBytes() {
        return new HashMap(this.classBytes);
    }

    public void flush() throws IOException {
    }

    public void close() throws IOException {
        this.classBytes.clear();
    }

    public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind, FileObject sibling) throws IOException {
        return (JavaFileObject)(kind == Kind.CLASS ? new MemoryJavaFileManager.MemoryOutputJavaFileObject(className) : super.getJavaFileForOutput(location, className, kind, sibling));
    }

    JavaFileObject makeStringSource(String name, String code) {
        return new MemoryJavaFileManager.MemoryInputJavaFileObject(name, code);
    }

    static class MemoryInputJavaFileObject extends SimpleJavaFileObject {
        final String code;

        MemoryInputJavaFileObject(String name, String code) {
            super(URI.create("string:///" + name), Kind.SOURCE);
            this.code = code;
        }

        public CharBuffer getCharContent(boolean ignoreEncodingErrors) {
            return CharBuffer.wrap(this.code);
        }
    }

    class MemoryOutputJavaFileObject extends SimpleJavaFileObject {
        final String name;

        MemoryOutputJavaFileObject(String name) {
            super(URI.create("string:///" + name), Kind.CLASS);
            this.name = name;
        }

        public OutputStream openOutputStream() {
            return new FilterOutputStream(new ByteArrayOutputStream()) {
                public void close() throws IOException {
                    this.out.close();
                    ByteArrayOutputStream bos = (ByteArrayOutputStream)this.out;
                    MemoryJavaFileManager.this.classBytes.put(MemoryOutputJavaFileObject.this.name, bos.toByteArray());
                }
            };
        }
    }
}


