//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

// ## Stability: 0 - Unstable
package github.com.jminusminus.core;

public class Fs {

    // File is visible.
    public static final int F_OK = 0000;

    // File can be read.
    public static final int R_OK = 0444;

    // File can be written.
    public static final int W_OK = 0222;

    // File can be executed.
    public static final int X_OK = 0111;

    public static boolean access(String path) {
        return Fs.access(path, Fs.F_OK);
    }

    // Tests a user's permissions for the file specified by path. 
    // 'mode' is an optional integer that specifies the accessibility checks to be performed. 
    // The following constants define the possible values of mode. 
    // It is possible to create a mask consisting of the bitwise OR of two or more values.
    //
    // * Fs.F_OK - File is visible to the calling process. This is useful for determining if a file exists, but says nothing about rwx permissions. Default if no mode is specified.
    // * Fs.R_OK - File can be read by the calling process.
    // * Fs.W_OK - File can be written by the calling process.
    // * Fs.X_OK - File can be executed by the calling process.
    // 
    // If any of the accessibility checks fail, false will be returned. 
    // The following example checks if the file /tmp/foo.txt can be read and written by the current process.
    //
    // Example:
    // 
    // ```java
    // boolean b = Fs.access("/tmp/foo.txt", Fs.R_OK | Fs.W_OK);
    // 
    // boolean b = Fs.access("/tmp/foo.txt");
    // ```
    // Returns true on success and false on failure.
    public static boolean access(String path, int mode) {
        return Fs.accessFile(java.nio.file.Paths.get(path), mode);
    }

    public static boolean appendFile(String file, String data) {
        return Fs.appendFile(file, data, "utf8");
    }

    public static boolean appendFile(String file, String data, String encoding) {
        try {
            return Fs.appendFile(file, data.getBytes(encoding));
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // Append data to a file, creating the file if it does not yet exist. Data can be a string or byte array.
    //
    // Example:
    //
    // ```java
    // boolean b = Fs.appendFile("/tmp/foo.txt", "data to append".getBytes());
    //
    // boolean b = Fs.appendFile("/tmp/foo.txt", "data to append", "utf8");
    //
    // boolean b = Fs.appendFile("/tmp/foo.txt", "data to append");
    // // defaults to utf8
    // ```
    // Returns true on success and false on failure.
    public static boolean appendFile(String file, byte[] data) {
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(file), data, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    // Change the permissions for the file specified by path.
    //
    // Example:
    //
    // ```java
    // boolean b = Fs.chmod("/tmp/foo.txt", Fs.R_OK | Fs.W_OK);
    // ```
    // Returns true on success and false on failure.
    public static boolean chmod(String path, int mode) {
        return Fs.chmodFile(new java.io.File(path), mode);
    }

    // Change the owner of the file specified by path.
    //
    // Example:
    //
    // ```java
    // boolean b = Fs.chown("/tmp/foo.txt", "nobody");
    // ```
    // Returns true on success and false on failure.
    public static boolean chown(String path, String uid) {
        try {
            java.nio.file.Path p = java.nio.file.Paths.get(path);
            java.nio.file.attribute.FileOwnerAttributeView view = java.nio.file.Files.getFileAttributeView(p, java.nio.file.attribute.FileOwnerAttributeView.class);
            java.nio.file.attribute.UserPrincipalLookupService lookupService = java.nio.file.FileSystems.getDefault().getUserPrincipalLookupService();
            java.nio.file.attribute.UserPrincipal userPrincipal = lookupService.lookupPrincipalByName(uid);
            java.nio.file.Files.setOwner(p, userPrincipal);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean mkdir(String path) {
        return Fs.mkdir(path, 0777);
    }

    // Create a directory at the specified path.
    // Default mode is 0777.
    //
    // Example:
    //
    // ```java
    // boolean b = Fs.mkdir("/tmp/foo");
    //
    // boolean b = Fs.mkdir("/tmp/foo", Fs.R_OK | Fs.W_OK | Fs.X_OK);
    // ```
    // Returns true on success and false on failure.
    public static boolean mkdir(String path, int mode) {
        try {
            java.nio.file.Files.createDirectory(java.nio.file.Paths.get(path));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean mkdirs(String path) {
        return Fs.mkdirs(path, 0777);
    }

    // Recursively create directories to the specified path.
    // Default mode is 0777.
    //
    // Example:
    //
    // ```java
    // boolean b = Fs.mkdirs("/tmp/foo/bar");
    //
    // boolean b = Fs.mkdirs("/tmp/foo/bar", Fs.R_OK | Fs.W_OK | Fs.X_OK);
    // ```
    // Returns true on success and false on failure.
    public static boolean mkdirs(String path, int mode) {
        try {
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get(path));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    // Reads the contents of a directory at the specified path and returns an array of the names 
    // of the files in the directory excluding '.' and '..'.
    // 
    // Example:
    //
    // ```java
    // String[] files = Fs.readdir("/tmp");
    // ```
    // If there is an error null is returned.
    public static String[] readdir(String path) {
        java.io.File folder = new java.io.File(path);
        java.io.File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            return null;
        }
        String[] files = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            files[i] = listOfFiles[i].getName();
        }
        return files;
    }

    // Reads the entire contents of the specified file into a byte array.
    // 
    // Example:
    //
    // ```java
    // byte[] f = Fs.readFile("/tmp/foo.txt");
    // ```
    // If there is an error null is returned.
    public static byte[] readFile(String file) {
        try {
            return java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(file));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Rename the specified path.
    // 
    // Example:
    //
    // ```java
    // boolean b = Fs.rename("/tmp/foo.txt", "/tmp/bar.txt");
    // ```
    // Returns true on success and false on failure.
    public static boolean rename(String oldPath, String newPath) {
        try {
            java.nio.file.Files.move(java.nio.file.Paths.get(oldPath), java.nio.file.Paths.get(newPath));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    // Remove the specified directory. The directory must be empty of all files and sub directories.
    // 
    // Example:
    //
    // ```java
    // boolean b = Fs.rmdir("/tmp/foo");
    // ```
    // Returns true on success and false on failure.
    public static boolean rmdir(String path) {
        return Fs.unlink(path);
    }

    // Returns the file status of specified path.
    // 
    // Example:
    //
    // ```java
    // Fs.Stat s = Fs.stat("/tmp/foo.txt");
    // ```
    // If there is an error null is returned.
    public static Stat stat(String path) {
        java.nio.file.attribute.BasicFileAttributes attrs;
        try {
            attrs = java.nio.file.Files.readAttributes(java.nio.file.Paths.get(path), java.nio.file.attribute.BasicFileAttributes.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        Stat stat = new Stat();
        stat.dir = attrs.isDirectory();
        stat.file = attrs.isRegularFile();
        stat.link = attrs.isSymbolicLink();
        stat.other = attrs.isOther();
        stat.size = attrs.size();
        stat.access = attrs.lastAccessTime().toMillis();
        stat.modify = attrs.lastModifiedTime().toMillis();
        stat.create = attrs.creationTime().toMillis();
        return stat;
    }

    // Truncate the specified file to the given length.
    // 
    // Example:
    //
    // ```java
    // boolean b = Fs.truncate("/tmp/foo.txt", 100);
    // ```
    // Returns true on success and false on failure.
    public static boolean truncate(String file, int len) {
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(file), new byte[len], java.nio.file.StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    // Deletes the specified file or directory if is exists.
    // 
    // Example:
    //
    // ```java
    // boolean b = Fs.unlink("/tmp/foo.txt");
    // ```
    // Returns true on success and false on failure.
    public static boolean unlink(String path) {
        try {
            java.nio.file.Files.delete(java.nio.file.Paths.get(path));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    // Changes the modified timestamp of the specified path.
    //
    // Example:
    //
    // ```java
    // boolean b = Fs.mtime("/tmp/foo.txt", (long)123456000);
    // ```
    // Returns true on success and false on failure.
    public static boolean mtime(String path, long time) {
        try {
            java.nio.file.Files.setLastModifiedTime(java.nio.file.Paths.get(path), java.nio.file.attribute.FileTime.fromMillis(time));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean writeFile(String file, String data) {
        return Fs.writeFile(file, data, "utf8");
    }

    public static boolean writeFile(String file, String data, String encoding) {
        try {
            return Fs.writeFile(file, data.getBytes(encoding));
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // Write data to a file, creating the file if it does not yet exist. Data can be a string or byte array.
    //
    // Example:
    //
    // ```java
    // boolean b = Fs.writeFile("/tmp/foo.txt", "data to write".getBytes());
    //
    // boolean b = Fs.writeFile("/tmp/foo.txt", "data to write", "utf8");
    //
    // boolean b = Fs.writeFile("/tmp/foo.txt", "data to write");
    // // defaults to utf8
    // ```
    // Returns true on success and false on failure.
    public static boolean writeFile(String file, byte[] data) {
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(file), data, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    // Check if the current user can access the given file with the given mode.
    protected static boolean accessFile(java.nio.file.Path p, int mode) {
        switch (mode) {
            case 0000:
                return Fs.access0000(p);
            case 0111:
                return Fs.access0111(p);
            case 0222:
                return Fs.access0222(p);
            case 0333:
                return Fs.access0333(p);
            case 0444:
                return Fs.access0444(p);
        }
        return Fs.accessFileReadWriteExecute(p, mode);
    }

    // Carry on from Fs.access() for complexity break down.
    protected static boolean accessFileReadWriteExecute(java.nio.file.Path p, int mode) {
        switch (mode) {
            case 0555:
                return Fs.access0555(p);
            case 0666:
                return Fs.access0666(p);
            case 0777:
                return Fs.access0777(p);
        }
        return false;
    }

    // Check if the mode of the given java.nio.file.Path is 0000.
    protected static boolean access0000(java.nio.file.Path p) {
        return java.nio.file.Files.exists(p);
    }

    // Check if the mode of the given java.nio.file.Path is 0111.
    protected static boolean access0111(java.nio.file.Path p) {
        return java.nio.file.Files.isExecutable(p);
    }

    // Check if the mode of the given java.nio.file.Path is 0222.
    protected static boolean access0222(java.nio.file.Path p) {
        return java.nio.file.Files.isWritable(p);
    }

    // Check if the mode of the given java.nio.file.Path is 0333.
    protected static boolean access0333(java.nio.file.Path p) {
        return java.nio.file.Files.isWritable(p) && java.nio.file.Files.isExecutable(p);
    }

    // Check if the mode of the given java.nio.file.Path is 0444.
    protected static boolean access0444(java.nio.file.Path p) {
        return java.nio.file.Files.isReadable(p);
    }

    // Check if the mode of the given java.nio.file.Path is 0555.
    protected static boolean access0555(java.nio.file.Path p) {
        return java.nio.file.Files.isReadable(p) && java.nio.file.Files.isExecutable(p);
    }

    // Check if the mode of the given java.nio.file.Path is 0666.
    protected static boolean access0666(java.nio.file.Path p) {
        return java.nio.file.Files.isReadable(p) && java.nio.file.Files.isWritable(p);
    }

    // Check if the mode of the given java.nio.file.Path is 0777.
    protected static boolean access0777(java.nio.file.Path p) {
        return java.nio.file.Files.isReadable(p) && java.nio.file.Files.isWritable(p) && java.nio.file.Files.isExecutable(p);
    }

    // Change the mode of the given java.io.File to the given mode.
    protected static boolean chmodFile(java.io.File f, int mode) {
        switch (mode) {
            case 0000:
                return Fs.chmod0000(f);
            case 0111:
                return Fs.chmod0111(f);
            case 0222:
                return Fs.chmod0222(f);
            case 0333:
                return Fs.chmod0333(f);
            case 0444:
                return Fs.chmod0444(f);
        }
        return Fs.chmodFileReadWriteExecute(f, mode);
    }

    // Carry on from Fs.chmodFile() for complexity break down.
    protected static boolean chmodFileReadWriteExecute(java.io.File f, int mode) {
        switch (mode) {
            case 0555:
                return Fs.chmod0555(f);
            case 0666:
                return Fs.chmod0666(f);
            case 0777:
                return Fs.chmod0777(f);
        }
        return false;
    }

    // Change the mode of the given java.io.File to 0000.
    protected static boolean chmod0000(java.io.File f) {
        return f.setReadable(false) && f.setWritable(false) && f.setExecutable(false);
    }

    // Change the mode of the given java.io.File to 0111.
    protected static boolean chmod0111(java.io.File f) {
        return f.setReadable(false) && f.setWritable(false) && f.setExecutable(true);
    }

    // Change the mode of the given java.io.File to 0222.
    protected static boolean chmod0222(java.io.File f) {
        return f.setReadable(false) && f.setWritable(true) && f.setExecutable(false);
    }

    // Change the mode of the given java.io.File to 0333.
    protected static boolean chmod0333(java.io.File f) {
        return f.setReadable(false) && f.setWritable(true) && f.setExecutable(true);
    }

    // Change the mode of the given java.io.File to 0444.
    protected static boolean chmod0444(java.io.File f) {
        return f.setReadable(true) && f.setWritable(false) && f.setExecutable(false);
    }

    // Change the mode of the given java.io.File to 0555.
    protected static boolean chmod0555(java.io.File f) {
        return f.setReadable(true) && f.setWritable(false) && f.setExecutable(true);
    }

    // Change the mode of the given java.io.File to 0666.
    protected static boolean chmod0666(java.io.File f) {
        return f.setReadable(true) && f.setWritable(true) && f.setExecutable(false);
    }

    // Change the mode of the given java.io.File to 0777.
    protected static boolean chmod0777(java.io.File f) {
        return f.setReadable(true) && f.setWritable(true) && f.setExecutable(true);
    }
}
