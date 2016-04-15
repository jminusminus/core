
# Fs
#### Package: github.com.jminusminus.core
## Stability: 0 - Unstable

## Install
```
jmm get github.com/jminusminus/core
```
## Class: github.com.jminusminus.core.Fs
```
import github.com.jminusminus.core.Fs;
```
## Attributes
## static final int F_OK = 0000
File is visible.

## static final int R_OK = 0444
File can be read.

## static final int W_OK = 0222
File can be written.

## static final int X_OK = 0111
File can be executed.

## Methods
## static boolean access(String path)

## static boolean access(String path, int mode)
Tests a user's permissions for the file specified by path.
'mode' is an optional integer that specifies the accessibility checks to be performed.
The following constants define the possible values of mode.
It is possible to create a mask consisting of the bitwise OR of two or more values.

* Fs.F_OK - File is visible to the calling process. This is useful for determining if a file exists, but says nothing about rwx permissions. Default if no mode is specified.
* Fs.R_OK - File can be read by the calling process.
* Fs.W_OK - File can be written by the calling process.
* Fs.X_OK - File can be executed by the calling process.

If any of the accessibility checks fail, false will be returned.
The following example checks if the file /tmp/foo.txt can be read and written by the current process.

Example:

```java
boolean b = Fs.access("/tmp/foo.txt", Fs.R_OK | Fs.W_OK);

boolean b = Fs.access("/tmp/foo.txt");
```
Returns true on success and false on failure.

## static boolean appendFile(String file, String data)

## static boolean appendFile(String file, String data, String encoding)

## static boolean appendFile(String file, byte[] data)
Append data to a file, creating the file if it does not yet exist. Data can be a string or byte array.

Example:

```java
boolean b = Fs.appendFile("/tmp/foo.txt", "data to append".getBytes());

boolean b = Fs.appendFile("/tmp/foo.txt", "data to append", "utf8");

boolean b = Fs.appendFile("/tmp/foo.txt", "data to append");
// defaults to utf8
```
Returns true on success and false on failure.

## static boolean chmod(String path, int mode)
Change the permissions for the file specified by path.

Example:

```java
boolean b = Fs.chmod("/tmp/foo.txt", Fs.R_OK | Fs.W_OK);
```
Returns true on success and false on failure.

## static boolean chown(String path, String uid)
Change the owner of the file specified by path.

Example:

```java
boolean b = Fs.chown("/tmp/foo.txt", "nobody");
```
Returns true on success and false on failure.

## static boolean mkdir(String path)

## static boolean mkdir(String path, int mode)
Create a directory at the specified path.
Default mode is 0777.

Example:

```java
boolean b = Fs.mkdir("/tmp/foo");

boolean b = Fs.mkdir("/tmp/foo", Fs.R_OK | Fs.W_OK | Fs.X_OK);
```
Returns true on success and false on failure.

## static boolean mkdirs(String path)

## static boolean mkdirs(String path, int mode)
Recursively create directories to the specified path.
Default mode is 0777.

Example:

```java
boolean b = Fs.mkdirs("/tmp/foo/bar");

boolean b = Fs.mkdirs("/tmp/foo/bar", Fs.R_OK | Fs.W_OK | Fs.X_OK);
```
Returns true on success and false on failure.

## static String[] readdir(String path)
Reads the contents of a directory at the specified path and returns an array of the names
of the files in the directory excluding '.' and '..'.

Example:

```java
String[] files = Fs.readdir("/tmp");
```
If there is an error null is returned.

## static byte[] readFile(String file)
Reads the entire contents of the specified file into a byte array.

Example:

```java
byte[] f = Fs.readFile("/tmp/foo.txt");
```
If there is an error null is returned.

## static boolean rename(String oldPath, String newPath)
Rename the specified path.

Example:

```java
boolean b = Fs.rename("/tmp/foo.txt", "/tmp/bar.txt");
```
Returns true on success and false on failure.

## static boolean rmdir(String path)
Remove the specified directory. The directory must be empty of all files and sub directories.

Example:

```java
boolean b = Fs.rmdir("/tmp/foo");
```
Returns true on success and false on failure.

## static Stat stat(String path)
Returns the file status of specified path.

Example:

```java
Fs.Stat s = Fs.stat("/tmp/foo.txt");
```
If there is an error null is returned.

## static boolean truncate(String file, int len)
Truncate the specified file to the given length.

Example:

```java
boolean b = Fs.truncate("/tmp/foo.txt", 100);
```
Returns true on success and false on failure.

## static boolean unlink(String path)
Deletes the specified file or directory if is exists.

Example:

```java
boolean b = Fs.unlink("/tmp/foo.txt");
```
Returns true on success and false on failure.

## static boolean mtime(String path, long time)
Changes the modified timestamp of the specified path.

Example:

```java
boolean b = Fs.mtime("/tmp/foo.txt", (long)123456000);
```
Returns true on success and false on failure.

## static boolean writeFile(String file, String data)

## static boolean writeFile(String file, String data, String encoding)

## static boolean writeFile(String file, byte[] data)
Write data to a file, creating the file if it does not yet exist. Data can be a string or byte array.

Example:

```java
boolean b = Fs.writeFile("/tmp/foo.txt", "data to write".getBytes());

boolean b = Fs.writeFile("/tmp/foo.txt", "data to write", "utf8");

boolean b = Fs.writeFile("/tmp/foo.txt", "data to write");
// defaults to utf8
```
Returns true on success and false on failure.

