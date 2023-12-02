use std::result;

// Generic Types, Traits, and Lifetimes
fn main() {
    let num_list = vec![34, 50, 15, 100, 25, 65];
    let mut largest = &num_list[0];
    for num in &num_list {
        if num > largest {
            largest = num;
        }
    }
    println!("The largest number is {}", largest);
    println!("The largest number is {}", get_largest_i32(&num_list));

    let num_list = vec![102, 34, 6000, 89, 54, 2, 43, 8];
    let mut largest = &num_list[0];
    for num in &num_list {
        if num > largest {
            largest = num;
        }
    }
    println!("The largest number is {}", largest);
    println!("The largest number is {}", get_largest_i32(&num_list));
    println!("The largest number is {}", get_largest(&num_list));

    let chars = vec!['a', 'd', 'b', 'c', 'e'];
    println!("The largest char is {}", get_largest_char(&chars));
    println!("The largest char is {}", get_largest(&chars));

    let int_struct = Point { x: 13, y: 14 };
    print_type(&int_struct);

    let mix_struct = Point0 { x: 13, y: 14.0 };
    print_type(&mix_struct);

    println!("p.x: {}", int_struct.x());

    let mix_struct1 = Point { x: 13.0, y: 14.0 };
    println!(
        "distance_from_origin: {}",
        mix_struct1.distance_from_origin()
    );

    let pt10 = Point1 { x: 11, y: 7.8 };
    let pt11 = Point1 { x: 22, y: 'c' };
    dbg!(&pt10);
    dbg!(&pt11);
    let p101 = pt10.mixup(pt11);
    println!("mixup: {:?}", &p101);
    print_type(&p101);
}

enum Option<T> {
    Some(T),
    None,
}

enum Result<T, E> {
    Ok(T),
    Err(E),
}

impl<T> Point<T> {
    fn x(&self) -> &T {
        &self.x
    }
}

impl Point<f32> {
    fn distance_from_origin(&self) -> f32 {
        (self.x.powi(2) + self.y.powi(2)).sqrt()
    }
}

#[derive(Debug)]
struct Point1<X1, Y1> {
    x: X1,
    y: Y1,
}

impl<X1, Y1> Point1<X1, Y1> {
    fn mixup<X2, Y2>(self, other: Point1<X2, Y2>) -> Point1<X1, Y2> {
        Point1 {
            x: self.x,
            y: other.y,
        }
    }
}

fn get_largest_i32(num_list: &Vec<i32>) -> &i32 {
    print_type(num_list);
    let mut largest = &num_list[0];
    for num in num_list {
        if largest < num {
            largest = num;
        }
    }
    &largest
}

fn get_largest_char(list: &[char]) -> &char {
    let mut largest = &list[0];
    for chr in list {
        if chr > largest {
            largest = chr;
        }
    }

    &largest
}

// Generic Data Types

fn get_largest<T: std::cmp::PartialOrd>(list: &[T]) -> &T {
    let mut largest = &list[0];
    for item in list {
        if item > largest {
            largest = item;
        }
    }

    &largest
}

struct Point<T> {
    x: T,
    y: T,
}

struct Point0<T, U> {
    x: T,
    y: U,
}

fn print_type<T>(arg: &T) {
    println!("{:?}", std::any::type_name::<T>());
}
