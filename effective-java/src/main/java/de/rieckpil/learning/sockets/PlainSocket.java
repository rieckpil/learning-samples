package de.rieckpil.learning.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PlainSocket {

	public static void main(String[] args) throws IOException {

		ServerSocket socket = new ServerSocket(4000);
		Socket sock = socket.accept();
		sock.getOutputStream().write("Hello World!".getBytes());

	}
}
