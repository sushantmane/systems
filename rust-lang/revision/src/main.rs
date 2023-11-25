// A half-hour to learn Rust

use std::cmp::max;
use std::ops::Add;

fn main() {
    // variables
    let x;
    x = 42;

    // variables with explicit type
    let x: u32;
    // x = -1 <== compiler error
    x = 1;

    // int signed types: i8, i16, i32, i64, i128
    // unsigned int: u8, u16, u32, u64, u128

    let x: i32 = -1;

    let y;
    // println!("y: {y}"); <== compiler errror
    y = 20;

    // _ is a special name; lack of name; means throw away something
    let _ = 42;

    // names that start with an underscore are regular names but compiler won't warn about them
    // being unused
    let _z = 90;

    let x = 13;
    println!("x: {x}");
    let x = 13 + x; // shadow
    println!("x: {x}");

    // tuples: can container values of different types
    let pair = ('a', 19);
    println!("pair: {} {}", pair.0, pair.1);

    let pair: (char, i32) = ('b', 20);
    println!("pair: {} {}", pair.0, pair.1);

    let (some_char, int_a, int_b) = ('a', 19, 20);
    println!("{some_char}, {int_a}, {int_b}");

    // use _ to throw away part of tuple
    let (_, _, c): (_, _, char) = ('a', 'b', 'c');
    println!("{c}");

    let vec = vec![1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        .iter()
        .map(|x| x + 3)
        .fold(0, |x, y| x + y);
    println!("vec: {vec}");
    let x = 20;
    {
        let x = 'c';
        println!("this is a code block - St2 and x = {x}")
    }
    println!("x outside: {x}");

    // blocks are expressions; meaning that they evaluate to a value
    let x = { 20 * 4 };
    println!("x: {x}");

    println!("dice roll: {}", fair_dice_roll());

    // if conditionals are also expressions
    let y = if 34 == 43 { 't' } else { 'f' };
    println!("y: {y}");

    // a `match` is also an expression
    let y = match 10 == 10 {
        true => 't',
        false => 'f',
    };
    println!("y: {y}");

    // dots are typically used to access fields of a value
    let a = (10, 20);
    println!("{} {}", a.0, a.1);

    let emp = get_some_struct();
    println!("{} {}", emp.name, emp.id);

    // :: (double-colon)  operates on namespaces

    let least = std::cmp::min(3, 4);
    println!("least: {least}");

    // `use` directives can be used to 'bring in scope' names from other namespace
    use std::cmp::max;
    let max = max(9, 10);
    dbg!(max);

    // globs
    use std::cmp::{max_by, min};
    let least = min(9, 10);
    dbg!(least);

    // wildcard
    use std::cmp::*;

    // types are namespaces too
    let x = "baymax".len();
    dbg!(x);
    let x = str::len("baymax");
    dbg!(x);

    let mut v: Vec<i32> = Vec::new();
    let mut v: Vec<i32> = std::vec::Vec::new();
    use std::prelude::v1::*; // rust inserts this at the beginning of every module

    // struct
    struct Vec2 {
        x: f64,
        y: u32,
        z: char,
    }

    let v2 = Vec2 {
        x: 45.54f64,
        y: 34,
        z: 'c',
    };
    println!("v2.x: {} - v2.y: {} - v2.z: {}", v2.x, v2.y, v2.z);
    // init with some fields from another struct; "struct update syntax"
    let v3 = Vec2 { z: 'a', ..v2 };
    println!("v3.x: {} - v3.y: {} - v3.z: {}", v3.x, v3.y, v3.z);
    let v4 = Vec2 { ..v3 }; // init with all fields of v3
    println!("v4.x: {} - v4.y: {} - v4.z: {}", v4.x, v4.y, v4.z);

    // structs can be destructured
    let Vec2 { x, y, z } = v4;
    println!("{} {} {}", x, y, z);
    let Vec2 { x, .. } = v3;
    dbg!(x);
}

fn greet() {
    println!("Hi there");
}

fn fair_dice_roll() -> i32 {
    let feeling_lucky: bool = true;
    if feeling_lucky {
        6
    } else {
        4
    }
}

struct Employee {
    name: String,
    id: u32,
}

fn get_some_struct() -> Employee {
    Employee {
        name: String::from("Tiger"),
        id: 42,
    }
}
