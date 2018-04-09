package de.rieckpil.learning.sockets;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PlainSocket {

	public static void main(String[] args) throws IOException {

		ServerSocket socket = new ServerSocket(4000);

		while (true) {
			Socket sock = socket.accept();
			OutputStream os = sock.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("What's you name?");
			sock.close();
		}

	}
}
