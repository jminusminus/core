//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

// ## Stability: 0 - Unstable
package github.com.jminusminus.core;

public class Fs {

    public static boolean access(String path) {
        return Fs.access(path, 0000);
    }

    // Tests a user's permissions for the file specified by path. 
    // 'mode' is an optional integer that specifies the accessibility checks to be performed. 
    // The following constants define the possible values of mode. 
    // It is possible to create a mask consisting of the bitwise OR of two or more values.
    //
    // * 0000 - File is visible to the calling process. This is useful for determining if a file exists, but says nothing about rwx permissions. Default if no mode is specified.
    // * 0444 - File can be read by the calling process.
    // * 0222 - File can be written by the calling process.
    // * 0111 - File can be executed by the calling process. This has no effect on Windows (will behave like fs.F_OK).
    //
    // The final argument, callback, is a callback function that is invoked with a possible error argument. 
    // If any of the accessibility checks fail, the error argument will be populated. 
    // The following example checks if the file /etc/passwd can be read and written by the current process.
    public static boolean access(String path, int mode) {
        java.io.File f = new java.io.File(path);
        switch (mode) {
            case 0000:
                return f.exists();
            case 0111:
                return f.canExecute();
            case 0222:
                return f.canWrite();
            case 0333:
                return f.canWrite() && f.canExecute();
            case 0444:
                return f.canRead();
        }
        return Fs.accessReadWriteExecute(f, mode);
    }

    protected static boolean accessReadWriteExecute(java.io.File f, int mode) {
        switch (mode) {
            case 0555:
                return f.canRead() && f.canExecute();
            case 0666:
                return f.canRead() && f.canWrite();
            case 0777:
                return f.canRead() && f.canWrite() && f.canExecute();
        }
        return false;
    }

    // * file <String> filename
    // * data <String> | <Buffer>
    // * options <Object> | <String>
    // * encoding <String> | <Null> default = 'utf8'
    // * mode <Number> default = 0o666
    // * flag <String> default = 'a'
    // Asynchronously append data to a file, creating the file if it does not yet exist. data can be a string or a buffer.
    //
    // Example:
    //
    // fs.appendFile('message.txt', 'data to append', (err) => {
    //   if (err) throw err;
    //   console.log('The "data to append" was appended to file!');
    // });
    // If options is a string, then it specifies the encoding. Example:
    //
    // fs.appendFile('message.txt', 'data to append', 'utf8', callback);
    public static boolean appendFile(String file, byte[] data, String options) {
        return false;
    }

    // Synchronous chmod(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean chmod(String path, int mode) {
        return false;
    }

    // Synchronous chown(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean chown(String path, String uid, String gid) {
        return false;
    }

    // Synchronous close(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean close(String fd) {
        return false;
    }

    // Synchronous fchmod(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean fchmod(String fd, String mode) {
        return false;
    }

    // Synchronous fchown(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean fchown(String fd, String uid, String gid) {
        return false;
    }

    // Synchronous fdatasync(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean fdatasync(String fd) {
        return false;
    }

    // Synchronous fstat(2). The callback gets two arguments (err, stats) where stats is a fs.Stats object. fstat() is identical to stat(), except that the file to be stat-ed is specified by the file descriptor fd.
    public static String fstat(String fd) {
        return "";
    }

    // Synchronous fsync(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean fsync(String fd) {
        return false;
    }

    // Synchronous ftruncate(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean ftruncate(String fd, int len) {
        return false;
    }

    // Change the file timestamps of a file referenced by the supplied file descriptor.
    public static boolean futimes(String fd, int atime, int mtime) {
        return false;
    }

    // Synchronous lchmod(2). No arguments other than a possible exception are given to the completion callback.
    // 
    // Only available on Mac OS X.
    public static boolean lchmod(String path, String mode) {
        return false;
    }

    // Synchronous lchown(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean lchown(String path, String uid, String gid) {
        return false;
    }

    // Synchronous link(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean link(String srcpath, String dstpath) {
        return false;
    }

    // Synchronous lstat(2). The callback gets two arguments (err, stats) where stats is a fs.Stats object. lstat() is identical to stat(), except that if path is a symbolic link, then the link itself is stat-ed, not the file that it refers to.
    public static String lstat(String path) {
        return "";
    }

    // Synchronous mkdir(2). No arguments other than a possible exception are given to the completion callback. mode defaults to 0o777.
    public static boolean mkdir(String path, String mode) {
        return false;
    }

    // Synchronous file open. See open(2). flags can be:
    // 
    // 'r' - Open file for reading. An exception occurs if the file does not exist.
    // 
    // 'r+' - Open file for reading and writing. An exception occurs if the file does not exist.
    // 
    // 'rs' - Open file for reading in synchronous mode. Instructs the operating system to bypass the local file system cache.
    // 
    // This is primarily useful for opening files on NFS mounts as it allows you to skip the potentially stale local cache. It has a very real impact on I/O performance so don't use this flag unless you need it.
    // 
    // Note that this doesn't turn fs.open() into a synchronous blocking call. If that's what you want then you should be using fs.openSync()
    // 
    // 'rs+' - Open file for reading and writing, telling the OS to open it synchronously. See notes for 'rs' about using this with caution.
    // 
    // 'w' - Open file for writing. The file is created (if it does not exist) or truncated (if it exists).
    // 
    // 'wx' - Like 'w' but fails if path exists.
    // 
    // 'w+' - Open file for reading and writing. The file is created (if it does not exist) or truncated (if it exists).
    // 
    // 'wx+' - Like 'w+' but fails if path exists.
    // 
    // 'a' - Open file for appending. The file is created if it does not exist.
    // 
    // 'ax' - Like 'a' but fails if path exists.
    // 
    // 'a+' - Open file for reading and appending. The file is created if it does not exist.
    //
    // 'ax+' - Like 'a+' but fails if path exists.
    // 
    // mode sets the file mode (permission and sticky bits), but only if the file was created. It defaults to 0666, readable and writable.
    // 
    // The callback gets two arguments (err, fd).
    // 
    // The exclusive flag 'x' (O_EXCL flag in open(2)) ensures that path is newly created. On POSIX systems, path is considered to exist even if it is a symlink to a non-existent file. The exclusive flag may or may not work with network file systems.
    // 
    // flags can also be a number as documented by open(2); commonly used constants are available from require('constants'). On Windows, flags are translated to their equivalent ones where applicable, e.g. O_WRONLY to FILE_GENERIC_WRITE, or O_EXCL|O_CREAT to CREATE_NEW, as accepted by CreateFileW.
    // 
    // On Linux, positional writes don't work when the file is opened in append mode. The kernel ignores the position argument and always appends the data to the end of the file.
    public static String open(String path, String flags, String mode) {
        return "";
    }

    // Read data from the file specified by fd.
    // 
    // buffer is the buffer that the data will be written to.
    // 
    // offset is the offset in the buffer to start writing at.
    // 
    // length is an integer specifying the number of bytes to read.
    // 
    // position is an integer specifying where to begin reading from in the file. If position is null, data will be read from the current file position.
    // 
    // The callback is given the three arguments, (err, bytesRead, buffer).
    public static int read(String fd, byte[] buffer, int offset, int length, int position) {
        return 0;
    }

    // Synchronous readdir(3). Reads the contents of a directory.
    // Returns an array of the names of the files in the directory excluding '.' and '..'.
    // If there was an error null is returned.
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

    // file <String> filename
    // options <Object> | <String>
    // encoding <String> | <Null> default = null
    // flag <String> default = 'r'
    // callback <Function>
    // Asynchronously reads the entire contents of a file. Example:
    // 
    // fs.readFile('/etc/passwd', (err, data) => {
    //   if (err) throw err;
    //   console.log(data);
    // });
    // The callback is passed two arguments (err, data), where data is the contents of the file.
    // 
    // If no encoding is specified, then the raw buffer is returned.
    // 
    // If options is a string, then it specifies the encoding. Example:
    // 
    // fs.readFile('/etc/passwd', 'utf8', callback);
    public static byte[] readFile(String file, String options) {
        return new byte[0];
    }

    // Synchronous readlink(2). The callback gets two arguments (err, linkString).
    public static String readlink(String path) {
        return "";
    }

    // Synchronous rename(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean rename(String oldPath, String newPath) {
        return false;
    }

    // Synchronous rmdir(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean rmdir(String path) {
        return false;
    }

    // Synchronous stat(2). The callback gets two arguments (err, stats) where stats is a fs.Stats object. See the fs.Stats section for more information.
    public static String stat(String path) {
        return "";
    }

    // Synchronous symlink(2). No arguments other than a possible exception are given to the completion callback. The type argument can be set to 'dir', 'file', or 'junction' (default is 'file') and is only available on Windows (ignored on other platforms). Note that Windows junction points require the destination path to be absolute. When using 'junction', the target argument will automatically be normalized to absolute path.
    // 
    // Here is an example below:
    // 
    // fs.symlink('./foo', './new-port');
    // It creates a symbolic link named "new-port" that points to "foo".
    public static boolean symlink(String target, String path, String type) {
        return false;
    }

    // Synchronous truncate(2). No arguments other than a possible exception are given to the completion callback. A file descriptor can also be passed as the first argument. In this case, fs.ftruncate() is called.
    public static boolean truncate(String path, int len) {
        return false;
    }

    // Synchronous unlink(2). No arguments other than a possible exception are given to the completion callback.
    public static boolean unlink(String path) {
        return false;
    }

    // Stop watching for changes on filename. If listener is specified, only that particular listener is removed. Otherwise, all listeners are removed and you have effectively stopped watching filename.
    // 
    // Calling fs.unwatchFile() with a filename that is not being watched is a no-op, not an error.
    // 
    // Note: fs.watch() is more efficient than fs.watchFile() and fs.unwatchFile(). fs.watch() should be used instead of fs.watchFile() and fs.unwatchFile() when possible.
    public static boolean unwatchFile(String filename, String listener) {
        return false;
    }

    // Change file timestamps of the file referenced by the supplied path.
    // 
    // Note: the arguments atime and mtime of the following related functions does follow the below rules:
    // 
    // If the value is a numberable string like '123456789', the value would get converted to corresponding number.
    // If the value is NaN or Infinity, the value would get converted to Date.now().
    public static boolean utimes(String path, int atime, int mtime) {
        return false;
    }

    // Watch for changes on filename, where filename is either a file or a directory. The returned object is a fs.FSWatcher.
    // 
    // The second argument is optional. The options if provided should be an object. The supported boolean members are persistent and recursive. persistent indicates whether the process should continue to run as long as files are being watched. recursive indicates whether all subdirectories should be watched, or only the current directory. This applies when a directory is specified, and only on supported platforms (See Caveats).
    // 
    // The default is { persistent: true, recursive: false }.
    // 
    // The listener callback gets two arguments (event, filename). event is either 'rename' or 'change', and filename is the name of the file which triggered the event.
    // 
    // #### Caveats
    // 
    // The fs.watch API is not 100% consistent across platforms, and is unavailable in some situations.
    // 
    // The recursive option is only supported on OS X and Windows.
    //
    // #### Availability
    // 
    // This feature depends on the underlying operating system providing a way to be notified of filesystem changes.
    // 
    // On Linux systems, this uses inotify.
    // On BSD systems, this uses kqueue.
    // On OS X, this uses kqueue for files and 'FSEvents' for directories.
    // On SunOS systems (including Solaris and SmartOS), this uses event ports.
    // On Windows systems, this feature depends on ReadDirectoryChangesW.
    // If the underlying functionality is not available for some reason, then fs.watch will not be able to function. For example, watching files or directories on network file systems (NFS, SMB, etc.) often doesn't work reliably or at all.
    // 
    // You can still use fs.watchFile, which uses stat polling, but it is slower and less reliable.
    // 
    // Filename Argument#
    // 
    // Providing filename argument in the callback is only supported on Linux and Windows. Even on supported platforms, filename is not always guaranteed to be provided. Therefore, don't assume that filename argument is always provided in the callback, and have some fallback logic if it is null.
    // 
    // fs.watch('somedir', (event, filename) => {
    //   console.log(`event is: ${event}`);
    //   if (filename) {
    //     console.log(`filename provided: ${filename}`);
    //   } else {
    //     console.log('filename not provided');
    //   }
    // });
    public static boolean watch(String filename, String options, String listener) {
        return false;
    }

    // Watch for changes on filename. The callback listener will be called each time the file is accessed.
    // 
    // The options argument may be omitted. If provided, it should be an object. The options object may contain a boolean named persistent that indicates whether the process should continue to run as long as files are being watched. The options object may specify an interval property indicating how often the target should be polled in milliseconds. The default is { persistent: true, interval: 5007 }.
    // 
    // The listener gets two arguments the current stat object and the previous stat object:
    // 
    // fs.watchFile('message.text', (curr, prev) => {
    //   console.log(`the current mtime is: ${curr.mtime}`);
    //   console.log(`the previous mtime was: ${prev.mtime}`);
    // });
    // These stat objects are instances of fs.Stat.
    // 
    // If you want to be notified when the file was modified, not just accessed, you need to compare curr.mtime and prev.mtime.
    // 
    // Note: when an fs.watchFile operation results in an ENOENT error, it will invoke the listener once, with all the fields zeroed (or, for dates, the Unix Epoch). In Windows, blksize and blocks fields will be undefined, instead of zero. If the file is created later on, the listener will be called again, with the latest stat objects. This is a change in functionality since v0.10.
    // 
    // Note: fs.watch() is more efficient than fs.watchFile and fs.unwatchFile. fs.watch should be used instead of fs.watchFile and fs.unwatchFile when possible.
    public static boolean watchFile(String filename, String options, String listener) {
        return false;
    }

    // Write data to the file specified by fd. If data is not a Buffer instance then the value will be coerced to a string.
    // 
    // position refers to the offset from the beginning of the file where this data should be written. If typeof position !== 'number' the data will be written at the current position. See pwrite(2).
    // 
    // encoding is the expected string encoding.
    // 
    // The callback will receive the arguments (err, written, string) where written specifies how many bytes the passed string required to be written. Note that bytes written is not the same as string characters. See Buffer.byteLength.
    // 
    // Unlike when writing buffer, the entire string must be written. No substring may be specified. This is because the byte offset of the resulting data may not be the same as the string offset.
    // 
    // Note that it is unsafe to use fs.write multiple times on the same file without waiting for the callback. For this scenario, fs.createWriteStream is strongly recommended.
    // 
    // On Linux, positional writes don't work when the file is opened in append mode. The kernel ignores the position argument and always appends the data to the end of the file.
    // 
    // fs.writeFile(file, data[, options], callback)#
    // file <String> filename
    // data <String> | <Buffer>
    // options <Object> | <String>
    // encoding <String> | <Null> default = 'utf8'
    // mode <Number> default = 0o666
    // flag <String> default = 'w'
    // callback <Function>
    // Asynchronously writes data to a file, replacing the file if it already exists. data can be a string or a buffer.
    // 
    // The encoding option is ignored if data is a buffer. It defaults to 'utf8'.
    // 
    // Example:
    // 
    // fs.writeFile('message.txt', 'Hello Node.js', (err) => {
    //   if (err) throw err;
    //   console.log('It\'s saved!');
    // });
    // If options is a string, then it specifies the encoding. Example:
    // 
    // fs.writeFile('message.txt', 'Hello Node.js', 'utf8', callback);
    // Note that it is unsafe to use fs.writeFile multiple times on the same file without waiting for the callback. For this scenario, fs.createWriteStream is strongly recommended.
    public static int write(String fd, byte[] data, int position, String encoding) {
        return 0;
    }
}
