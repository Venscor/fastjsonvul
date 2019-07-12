package com.venscor.dbcp;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

public class JavaStringCompiler {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    StandardJavaFileManager stdManager;

    public JavaStringCompiler() {
        if (this.compiler == null) {
            System.out.println("error");
        }

        this.stdManager = this.compiler.getStandardFileManager((DiagnosticListener) null, (Locale) null, (Charset) null);
    }

    public Map<String, byte[]> compile(String fileName, String source) throws IOException {
        MemoryJavaFileManager manager = new MemoryJavaFileManager(this.stdManager);
        JavaFileObject javaFileObject = manager.makeStringSource(fileName, source);
        CompilationTask task = this.compiler.getTask((Writer) null, manager, (DiagnosticListener) null, (Iterable) null, (Iterable) null, Arrays.asList(javaFileObject));
        Boolean result = task.call();
        if (result != null && result.booleanValue()) {
            return manager.getClassBytes();
        } else {
            throw new RuntimeException("Compilation failed.");
        }
    }
}

