//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

// ## Stability: 0 - Unstable
// This package contains utilities for handling and transforming file paths. Almost all these methods perform only string transformations. The file system is not consulted to check whether paths are valid.
package github.com.jminusminus.path;

public class Path {

    // The platform-specific path delimiter, ; or ':'.
    //
    // An example on *nix:
    // ```
    // console.log(process.env.PATH)
    // // '/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin'
    // 
    // process.env.PATH.split(path.delimiter)
    // // returns ['/usr/bin', '/bin', '/usr/sbin', '/sbin', '/usr/local/bin']
    // ```
    // An example on Windows:
    // ```
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
    //```
    // 'foo/bar/baz'.split(path.sep)
    // // returns ['foo', 'bar', 'baz']
    //```
    // An example on Windows:
    //```
    // 'foo\\bar\\baz'.split(path.sep)
    // // returns ['foo', 'bar', 'baz']
    //```
    public static final String sep = "/";

    public static String basename(String p) {
        return "";
    }

    // Return the last portion of a path. Similar to the Unix basename command.
    //
    // Example:
    //```
    // path.basename('/foo/bar/baz/asdf/quux.html')
    // // returns 'quux.html'
    //
    // path.basename('/foo/bar/baz/asdf/quux.html', '.html')
    // // returns 'quux'
    //```
    public static String basename(String p, String ext) {
        return "";
    }

    // Return the directory name of a path. Similar to the Unix dirname command.
    //
    // Example:
    // ```
    // path.dirname('/foo/bar/baz/asdf/quux')
    // // returns '/foo/bar/baz/asdf'
    // ```
    public static String dirname(String p) {
        return "";
    }

    // Return the extension of the path, from the last '.' to end of string in the last portion of the path. If there is no '.' in the last portion of the path or the first character of it is '.', then it returns an empty string. Examples:
    //```
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
    //```
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
    //```
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
    //```
    public static String format() {
        return "";
    }

    // Determines whether path is an absolute path. An absolute path will always resolve to the same location, regardless of the working directory.
    //
    // Examples:
    //```
    // Path.isAbsolute('/foo/bar') // true
    // Path.isAbsolute('/baz/..')  // true
    // Path.isAbsolute('qux/')     // false
    // Path.isAbsolute('.')        // false
    // Path.isAbsolute('')         // false
    // Path.isAbsolute(null)       // false
    //```
    // Note: If the path string passed as parameter is a zero-length string, unlike other path module functions, it will be used as-is and false will be returned.
    public static String isAbsolute(String p) {
        return "";
    }

    // Join all arguments together and normalize the resulting path.
    //
    // Arguments must be strings. In v0.8, non-string arguments were silently ignored. In v0.10 and up, an exception is thrown.
    //
    // Example:
    //```
    // path.join('/foo', 'bar', 'baz/asdf', 'quux', '..')
    // // returns '/foo/bar/baz/asdf'
    //```
    // Note: If the arguments to join have zero-length strings, unlike other path module functions, they will be ignored. If the joined path string is a zero-length string then '.' will be returned, which represents the current working directory.
    public static String join(String p) {
        return "";
    }

    // Normalize a string path, taking care of '..' and '.' parts.
    //
    // When multiple slashes are found, they're replaced by a single one; when the path contains a trailing slash, it is preserved. On Windows backslashes are used.
    //
    // Example:
    //```
    // path.normalize('/foo/bar//baz/asdf/quux/..')
    // // returns '/foo/bar/baz/asdf'
    //```
    // Note: If the path string passed as argument is a zero-length string then '.' will be returned, which represents the current working directory.
    public static String normalize(String p) {
        return "";
    }

    // Returns an object from a path string.
    //
    // An example on *nix:
    //```
    // path.parse('/home/user/dir/file.txt')
    // // returns
    // // {
    // //    root : "/",
    // //    dir : "/home/user/dir",
    // //    base : "file.txt",
    // //    ext : ".txt",
    // //    name : "file"
    // // }
    //```
    public static String parse(String p) {
        return "";
    }

    // Solve the relative path from from to to.
    // 
    // At times we have two absolute paths, and we need to derive the relative path from one to the other. This is actually the reverse transform of path.resolve, which means we see that:
    // ```
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
    // ```
    // path.resolve('foo/bar', '/tmp/file/', '..', 'a/../subfile');
    // ```
    // Is similar to:
    // ```
    // cd foo/bar
    // cd /tmp/file/
    // cd ..
    // cd a/../subfile
    // pwd
    // ```
    // The difference is that the different paths don't need to exist and may also be files.
    // 
    // Examples:
    // ```
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
}
