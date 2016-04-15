
# Path
#### Package: github.com.jminusminus.core
## Stability: 0 - Unstable
This package contains utilities for handling and transforming file paths. Almost all these methods perform only string transformations. The file system is not consulted to check whether paths are valid.

## Install
```
jmm get github.com/jminusminus/core
```
## Class: github.com.jminusminus.core.Path
```
import github.com.jminusminus.core.Path;
```
## Attributes
## String root = ""
The path root.

## String dir = ""
The directory path.

## String base = ""
The directory basename.

## String ext = ""
The file extension.

## String name = ""
The file name.

## static final String delimiter = ":"
The platform-specific path delimiter, ";" or ":".

## static final String sep = "/"
The platform-specific file separator. "\\" or "/".

## static final String home = "~"
The platform-specific home directory. "%HOMEPATH%" or "~".

## static final String cwd = "."
The platform-specific current working directory. "." or ".".

## Methods
## static String basename(String p)

## static String basename(String p, String ext)
Return the last portion of the specified path. Similar to the Unix basename command.

Example:

```java
String p = Path.basename("/foo/bar/baz/asdf/quux.html");
// returns "quux.html"

String p = Path.basename("/foo/bar/baz/asdf/quux.html", ".html");
// returns "quux"
```

## static String dirname(String p)
Return the directory name of the specified path. Similar to the Unix dirname command.

Example:

```java
String p = Path.dirname("/foo/bar/baz/asdf/quux");
// returns "/foo/bar/baz/asdf"
```

## static String extname(String p)
Return the extension of the specified path, from the last `.` to end of string in the last portion of
the path. If there is no `.` in the last portion of the path or the first character of it
is `.`, then it returns an empty string.

Examples:

```java
String e = Path.extname("index.html");
// returns ".html"

String e = Path.extname("index.coffee.md");
// returns ".md"

String e = Path.extname("index.");
// returns "."

String e = Path.extname("index");
// returns ""

String e = Path.extname(".index");
// returns ""
```

## String toString()
Returns a path string from the current Path object. This is the opposite of Path.parse().

If the Path Object has dir and base properties, the returned string will be a concatenation
of the dir property, the platform-dependent path separator, and the base property.

If the dir property is not supplied, the root property will be used as the dir property.
However, it will be assumed that the root property already ends with the platform-dependent
path separator. In this case, the returned string will be the concatenation of the root
property and the base property.

If both the dir and the root properties are not supplied, then the returned string will
be the contents of the base property.

If the base property is not supplied, a concatenation of the name property and the ext
property will be used as the base property.

Example:

```java
Path = {
    root : "/",
    dir : "/home/user/dir",
    base : "file.txt",
    ext : ".txt",
    name : "file"
}

String p = Path.toString();
// returns "/home/user/dir/file.txt"
```

`root` will be used if `dir` is not specified and `name` + `ext` will be used if `base` is not specified.

```java
Path = {
    root : "/",
    ext : ".txt",
    name : "file"
}

String p = Path.toString();
// returns "/file.txt"
```

## static boolean isAbsolute(String p)
Determines whether the specified path is an absolute path. An absolute path will always
resolve to the same location, regardless of the working directory.

Examples:

```java
boolean b = Path.isAbsolute("/foo/bar"); // true
boolean b = Path.isAbsolute("/baz/..");  // true
boolean b = Path.isAbsolute("qux/");     // false
boolean b = Path.isAbsolute(".");        // false
boolean b = Path.isAbsolute("");         // false
boolean b = Path.isAbsolute(null);       // false
```

Note: If the path string passed as parameter is a zero-length string false will be returned.

## static String join(String... p)
Join all arguments together and normalize the resulting path.

Example:

```java
String p = Path.join("/foo", "bar", "baz/asdf", "quux", "..");
// returns "/foo/bar/baz/asdf"

String p = Path.join("..", "bar", "baz/asdf", "quux", "..");
// returns "./baz/asdf"
```
Note: If the arguments to join have zero-length strings they will be ignored. If the joined path string
is a zero-length string then `.` will be returned, which represents the current working directory.

## static String normalize(String p)
Normalize the specified path, taking care of `..` and `.` parts.

When multiple slashes are found, they're replaced by a single one; when the path contains
a trailing slash, it is NOT preserved. On Windows backslashes are used.

Example:

```java
String = Path.normalize("/foo/bar//baz/asdf/quux/..");
// returns "/foo/bar/baz/asdf"
```

Note: If the path string passed as argument is a zero-length string then "." will be returned,
which represents the current working directory.

## static Path parse(String p)
Returns an object from a path string.

An example on *nix:

```java
Fs.Path p = Path.parse("/home/user/dir/file.txt");
// returns
// Path = {
//    root : "/",
//    dir : "/home/user/dir",
//    base : "file.txt",
//    ext : ".txt",
//    name : "file"
// }
```
If there is an error null is returned.

## static String relative(String from, String to)
Solve the relative path from from to to.

At times we have two absolute paths, and we need to derive the relative path from one to the other.
This is actually the reverse transform of Path.resolve(), which means we see that:

```java
String p = Path.resolve(from, Path.relative(from, to)) == Path.resolve(to);
```

Examples:

```java
String p = Path.relative("/a/b/c/d", "/a/b/e/f");
// returns "../../e/f"

String p = Path.relative("/a/b/c", "/e/f/g");
// returns "../../../e/f/g"
```

Note: If the arguments to relative have zero-length strings then the current working directory
will be used instead of the zero-length strings. If both the paths are the same then a
zero-length string will be returned.

## static String resolve(String... parts)
Resolves `to` to an absolute path.

If `to` isn't already absolute `from` arguments are pre-pended in right to left order, until an
absolute path is found. If after using all `from` paths still no absolute path is found, the
current working directory is used as well. The resulting path is normalized, and trailing
slashes are removed unless the path gets resolved to the root directory.

Another way to think of it is as a sequence of cd commands in a shell.

Is similar to:

```java
cd foo/bar
cd /tmp/file/
cd ..
cd a/../subfile
pwd
```

The difference is that the different paths don't need to exist and may also be files.

Examples:

```java
String p = Path.resolve("/foo/bar", "./baz");
// returns "/foo/bar/baz"

String p = Path.resolve("/foo/bar", "/tmp/file/");
// returns "/tmp/file"

String p = Path.resolve("wwwroot", "static_files/png/", "../gif/image.gif");
// if currently in /home/myself/node, it returns
// "/home/myself/node/wwwroot/static_files/gif/image.gif"
```
Note: If the arguments to resolve have zero-length strings then the current working directory
will be used instead of them.

