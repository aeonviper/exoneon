package exoneon.hotdeploy;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ReportLoader extends ClassLoader {
	public ReportLoader(ClassLoader parent) {
		super(parent);
	}

	public ReportLoader() {
		super(Report.class.getClassLoader());
	}

	public Class loadClass(String name) throws ClassNotFoundException {
		if (name.startsWith("exoneon.hotdeploy.report.")) {
			System.out.println(this.getClass().getName() + " loading class " + name);
			return getClass(name);
		}
		return super.loadClass(name);
	}

	public Class getClass(String name) throws ClassNotFoundException {
		String file = name.replace('.', File.separatorChar) + ".class";
		byte[] buffer = null;
		try {
			buffer = readFile(file);
			Class clazz = defineClass(name, buffer, 0, buffer.length);
			resolveClass(clazz);
			return clazz;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public byte[] readFile(String name) throws IOException {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		in.readFully(buff);
		in.close();
		return buff;
	}
}
