// # Chapter 9: Error Handling
// - recoverable vs unrecoverable

use std::fs::File;
use std::io::{self, Error};
use std::io::{ErrorKind, Read};

fn main() {
    // panic!("crash and burn");
    let v = vec![1, 2, 3];
    // v[99]; // panic

    // Recoverable Errors with `Result`
    // enum Result<T, E> {
    //     Ok(T),
    //     Err(E),
    // }

    let file_res = File::open("hello.txt");
    print_type(&file_res); // core::result::Result<std::fs::File, std::io::error::Error>

    let fh = match file_res {
        Ok(file) => file,
        Err(err) => match err.kind() {
            ErrorKind::NotFound => match File::create("hello.txt") {
                Ok(fc) => fc,
                Err(e) => panic!("Failed to create the file {:?}", err),
            },
            other => {
                panic!("Failed to open the file {:?}", other);
            }
        },
    };
    print_type(&fh);

    // alternative to match + result
    let fh = File::open("hello.txt").unwrap_or_else(|err| {
        if err.kind() == ErrorKind::NotFound {
            File::create("hello.txt").unwrap_or_else(|err| {
                panic!("Failed to create the file: {:?}", err);
            })
        } else {
            panic!("Failed to open the file: {:?}", err);
        }
    });
    print_type(&fh);

    // Shortcuts for Panic on Error: `unwrap` and `expect`
    // let fh = File::open("hello.txt").unwrap();

    // expect is preferred way
    // let fh = File::open("hello.dat").expect("hello.dat should be included in the project");

    // Propagating Errors
    let result = read_username_from_file();
    dbg!(result);
}

fn read_username_from_file() -> Result<String, io::Error> {
    let uname_res = File::open("user.dat");
    // let uname_res = File::open("invalid.txt");

    let mut uname_file = match uname_res {
        Ok(f) => f,
        Err(e) => return Err(e),
    };

    let mut uname = String::new();

    match uname_file.read_to_string(&mut uname) {
        Ok(_) => Ok(uname),
        Err(e) => Err(e),
    }
}

fn print_type<T>(arg: &T) {
    println!("{}", std::any::type_name::<T>());
}
