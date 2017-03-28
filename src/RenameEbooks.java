package com.humidmail.utils.ebooks;

import java.util.regex.*;
import java.io.*;

public class RenameEbooks
{
    public static void main(String[] args) throws IOException {
	if (args.length != 1) {
	    print("Usage: rename-ebooks <directory of files>\n");
	    System.exit(1);
	}
	
	File dir = new File(args[0]);
	final Pattern ebookNamePattern = Pattern.compile("(?i)\\.(?:mobi|azw3|epub|prc)$");
	File[] ebooks = dir.listFiles(new FilenameFilter() {
	    public boolean accept(File dir, String name) {
		return ebookNamePattern.matcher(name).find();
	    }});
	if (ebooks == null || ebooks.length == 0) {
	    print("\"%s\" doesn't have any eBooks in it.\n", dir.getPath());
	    System.exit(2);
	}
	
	final Pattern renamePattern = Pattern.compile("(.+) - (\\S.* )?(\\S+)\\.(\\S{3,4})");
	for (File f : ebooks) {
	    Matcher m = renamePattern.matcher(f.getName());
	    if (m.matches()) {
		StringBuilder sb = new StringBuilder();
		sb.append(m.group(3));
		if (m.group(2) != null)
		    sb.append(", ").append(m.group(2).trim());
		sb.append(" - ").append(m.group(1)).append('.').append(m.group(4));
		String newName = sb.toString();
		print("Renaming \"%s\" to \"%s\"\n", f.getName(), newName);
		f.renameTo(new File(dir, newName));
	    } else {
		print("Leaving \"%s\" unchanged\n", f.getName());
	    }
	}
	System.exit(0);
    }

    private static void print(String fmt, Object... args) {
	System.out.printf(fmt, args);
    }
}
