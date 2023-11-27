use std::any::type_name;
use std::fmt::Display;

pub fn learn_part2() {
    foobar("left_str0", "right_str0");
    foobar("left_str1", 32);

    // ::<> turbofish syntax
    println!("{}", type_name::<(f64, char)>());

    let p_str = Pair {
        a: String::from("Hello"),
        b: String::from("new"),
    };
    print_type_name(&p_str);

    let p_int = Pair {
        a: 23i32,
        b: 32i32,
    };
    print_type_name(&p_int);

    let v_str: Vec<String> = Vec::new();
    let v_int: Vec<i32> = Vec::new();
    print_type_name(&v_str);
    print_type_name(&v_int);

}

// functions can be generic; they can have multiple type parameters
// Type parameters have constraints
fn foobar<L: Display, R: Display + PartialEq>(left: L, right: R) {
    print(left);
    print0(right);
}

fn print<T: Display>(val: T) {
    println!("Val: {}", val);
}

// longer syntax for type parameter constraints
fn print0<T>(val: T)
where
    T: Display + PartialEq
{
    println!("Val: {}", val);
}

// generic structs
struct Pair<T> {
    a: T,
    b: T,
}

fn print_type_name<T> (_val: &T) {
    println!("{}", std::any::type_name::<T>());
}