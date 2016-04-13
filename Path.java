//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

// [![Build Status](https://travis-ci.org/jminusminus/path.svg?branch=master)](https://travis-ci.org/jminusminus/path)
// ## Stability: 0 - Unstable
// This package contains utilities for handling and transforming file paths. Almost all these methods perform only string transformations. The file system is not consulted to check whether paths are valid.
package github.com.jminusminus.core;

public class Path {

    // The platform-specific path delimiter, ; or ':'.
    //
    // An example on *nix:
    // ```java
    // console.log(process.env.PATH)
    // // '/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin'
    // 
    // process.env.PATH.split(path.delimiter)
    // // returns ['/usr/bin', '/bin', '/usr/sbin', '/sbin', '/usr/local/bin']
    // ```
    // An example on Windows:
    // ```java
    // console.log(process.env.PATH)
    // // 'C:\Windows\system32;C:\Windows;C:\Program Files\node\'
    //
    // process.env.PATH.split(path.delimiter)
    // // returns ['C:\\Windows\\system32', 'C:\\Windows', 'C:\\Program Files\\node\\']
    // ```
    public static final String delimiter = ":";

    // The platform-specific file separator. '\\' or '/'.
    //
    // An example on *nix:
    //```java
    // 'foo/bar/baz'.split(path.sep)
    // // returns ['foo', 'bar', 'baz']
    //```
    // An example on Windows:
    //```java
    // 'foo\\bar\\baz'.split(path.sep)
    // // returns ['foo', 'bar', 'baz']
    //```
    public static final String sep = "/";

    // The platform-specific home directory. '%HOMEPATH%' or '~'.
    public static final String home = "~";

    public static String basename(String p) {
        return "";
    }

    // Return the last portion of a path. Similar to the Unix basename command.
    //
    // Example:
    // ```java
    // path.basename('/foo/bar/baz/asdf/quux.html')
    // // returns 'quux.html'
    //
    // path.basename('/foo/bar/baz/asdf/quux.html', '.html')
    // // returns 'quux'
    // ```
    public static String basename(String p, String ext) {
        return "";
    }

    // Return the directory name of a path. Similar to the Unix dirname command.
    //
    // Example:
    // ```java
    // path.dirname('/foo/bar/baz/asdf/quux')
    // // returns '/foo/bar/baz/asdf'
    // ```
    public static String dirname(String p) {
        return "";
    }

    // Return the extension of the path, from the last '.' to end of string in the last portion of the path. If there is no '.' in the last portion of the path or the first character of it is '.', then it returns an empty string. Examples:
    // ```java
    // path.extname('index.html')
    // // returns '.html'
    //
    // path.extname('index.coffee.md')
    // // returns '.md'
    //
    // path.extname('index.')
    // // returns '.'
    //
    // path.extname('index')
    // // returns ''
    //
    // path.extname('.index')
    // // returns ''
    // ```
    public static String extname(String p) {
        return "";
    }

    // Returns a path string from an object. This is the opposite of path.parse.
    //
    // If pathObject has dir and base properties, the returned string will be a concatenation of the dir property, the platform-dependent path separator, and the base property.
    //
    // If the dir property is not supplied, the root property will be used as the dir property. However, it will be assumed that the root property already ends with the platform-dependent path separator. In this case, the returned string will be the concatenation fo the root property and the base property.
    //
    // If both the dir and the root properties are not supplied, then the returned string will be the contents of the base property.
    //
    // If the base property is not supplied, a concatenation of the name property and the ext property will be used as the base property.
    //
    // Example:
    //
    // ```java
    // path.format({
    //     root : "/",
    //     dir : "/home/user/dir",
    //     base : "file.txt",
    //     ext : ".txt",
    //     name : "file"
    // });
    // // returns '/home/user/dir/file.txt'
    //
    // // `root` will be used if `dir` is not specified and `name` + `ext` will be used
    // // if `base` is not specified
    // path.format({
    //     root : "/",
    //     ext : ".txt",
    //     name : "file"
    // })
    // // returns '/file.txt'
    // ```
    public static String format() {
        return "";
    }

    // Determines whether path is an absolute path. An absolute path will always resolve to the same location, regardless of the working directory.
    //
    // Examples:
    // ```java
    // Path.isAbsolute('/foo/bar') // true
    // Path.isAbsolute('/baz/..')  // true
    // Path.isAbsolute('qux/')     // false
    // Path.isAbsolute('.')        // false
    // Path.isAbsolute('')         // false
    // Path.isAbsolute(null)       // false
    // ```
    // Note: If the path string passed as parameter is a zero-length string, unlike other path module functions, it will be used as-is and false will be returned.
    public static boolean isAbsolute(String p) {
        return Path.sep.equals(String.valueOf(p.charAt(0)));
    }

    // Join all arguments together and normalize the resulting path.
    //
    // Arguments must be strings. In v0.8, non-string arguments were silently ignored. In v0.10 and up, an exception is thrown.
    //
    // Example:
    // ```java
    // path.join('/foo', 'bar', 'baz/asdf', 'quux', '..')
    // // returns '/foo/bar/baz/asdf'
    // ```
    // Note: If the arguments to join have zero-length strings, unlike other path module functions, they will be ignored. If the joined path string is a zero-length string then '.' will be returned, which represents the current working directory.
    public static String join(String... p) {
        return Path.normalize(String.join(Path.sep, p));
    }

    // Normalize a string path, taking care of '..' and '.' parts.
    //
    // When multiple slashes are found, they're replaced by a single one; 
    // when the path contains a trailing slash, it is preserved. On Windows backslashes are used.
    //
    // Example:
    //
    // ```java
    // Path.normalize('/foo/bar//baz/asdf/quux/..')
    // // returns '/foo/bar/baz/asdf'
    // ```
    //
    // Note: If the path string passed as argument is a zero-length string then '.' will be returned, which represents the current working directory.
    public static String normalize(String p) {
        if (p.isEmpty()) {
            p = ".";
        }
        switch (String.valueOf(p.charAt(0))) {
            case Path.sep:
                break;
            case Path.home: // User home.
                p = System.getProperty("user.home") + Path.sep + p;
                break;
            case ".":
                if (p.length() > 1 && p.charAt(1) == '.') {

                    break;
                }
            default: // Current working directory.
                p = System.getProperty("user.dir") + Path.sep + p;
        }
        return Path.sep + Path.normalizeArray(p.split(Path.sep));
    }

    // Returns an object from a path string.
    //
    // An example on *nix:
    // ```java
    // path.parse('/home/user/dir/file.txt')
    // // returns
    // // {
    // //    root : "/",
    // //    dir : "/home/user/dir",
    // //    base : "file.txt",
    // //    ext : ".txt",
    // //    name : "file"
    // // }
    // ```
    public static String parse(String p) {
        return "";
    }

    // Solve the relative path from from to to.
    // 
    // At times we have two absolute paths, and we need to derive the relative path from one to the other. This is actually the reverse transform of path.resolve, which means we see that:
    // ```java
    // path.resolve(from, path.relative(from, to)) == path.resolve(to)
    // Examples:
    // 
    // path.relative('C:\\orandea\\test\\aaa', 'C:\\orandea\\impl\\bbb')
    // // returns '..\\..\\impl\\bbb'
    // 
    // path.relative('/data/orandea/test/aaa', '/data/orandea/impl/bbb')
    // // returns '../../impl/bbb'
    // ```
    // Note: If the arguments to relative have zero-length strings then the current working directory will be used instead of the zero-length strings. If both the paths are the same then a zero-length string will be returned.
    public static String relative(String from, String to) {
        return "";
    }

    // Resolves to to an absolute path.
    // 
    // If to isn't already absolute from arguments are prepended in right to left order, until an absolute path is found. If after using all from paths still no absolute path is found, the current working directory is used as well. The resulting path is normalized, and trailing slashes are removed unless the path gets resolved to the root directory. Non-string from arguments are ignored.
    // 
    // Another way to think of it is as a sequence of cd commands in a shell.
    // ```java
    // path.resolve('foo/bar', '/tmp/file/', '..', 'a/../subfile');
    // ```
    // Is similar to:
    // ```java
    // cd foo/bar
    // cd /tmp/file/
    // cd ..
    // cd a/../subfile
    // pwd
    // ```
    // The difference is that the different paths don't need to exist and may also be files.
    // 
    // Examples:
    // ```java
    // path.resolve('/foo/bar', './baz')
    // // returns '/foo/bar/baz'
    // 
    // path.resolve('/foo/bar', '/tmp/file/')
    // // returns '/tmp/file'
    // 
    // path.resolve('wwwroot', 'static_files/png/', '../gif/image.gif')
    // // if currently in /home/myself/node, it returns
    // // '/home/myself/node/wwwroot/static_files/gif/image.gif'
    // ```
    // Note: If the arguments to resolve have zero-length strings then the current working directory will be used instead of them.
    public static String resolve(String... parts) {
        return "";
    }

    // Resolves . and .. elements in a path array with directory names there
    // must be no slashes or device names (c:\) in the array
    // (so also no leading and trailing slashes - it does not distinguish relative and absolute paths).
    protected static String normalizeArray(String[] parts) {
        String path = "";
        for (String part : parts) {
            // Ignore empty parts.
            if (part.isEmpty() || ".".equals(part)) {
                continue;
            }
            if ("..".equals(part) && path.length() > 0) {
                // Remove the last directory.
                path = path.substring(0, path.lastIndexOf(Path.sep));
            } else if (!"..".equals(part)) {
                path += Path.sep + part;
            }
        }
        // Remove the first separator.
        return path.substring(1);
    }
}
