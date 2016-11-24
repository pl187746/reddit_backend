package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputOutputStreamPump {

	private final InputStream inputStream;

	private final OutputStream outputStream;

	private final byte[] buffer;

	private int total = 0;

	public static final int DEFAULT_BUFFER_SIZE = 1024;

	public InputOutputStreamPump(InputStream inputStream, OutputStream outputStream, int bufferSize) {
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.buffer = new byte[bufferSize];
	}

	public InputOutputStreamPump(InputStream inputStream, OutputStream outputStream) {
		this(inputStream, outputStream, DEFAULT_BUFFER_SIZE);
	}

	public int getBufferSize() {
		return buffer.length;
	}

	public int getTotal() {
		return total;
	}

	public int pump() throws IOException {
		int read = inputStream.read(buffer);
		if (read > 0) {
			outputStream.write(buffer, 0, read);
			total += read;
		}
		return read;
	}

	public int pumpAll() throws IOException {
		while(pump() > 0);
		return getTotal();
	}

	public static int pumpAll(InputStream inputStream, OutputStream outputStream, int bufferSize) throws IOException {
		InputOutputStreamPump pump = new InputOutputStreamPump(inputStream, outputStream, bufferSize);
		return pump.pumpAll();
	}

	public static int pumpAll(InputStream inputStream, OutputStream outputStream) throws IOException {
		InputOutputStreamPump pump = new InputOutputStreamPump(inputStream, outputStream);
		return pump.pumpAll();
	}

}
