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
    println!("The largest number is {}", get_largest(&num_list));

    let num_list = vec![102, 34, 6000, 89, 54, 2, 43, 8];
    let mut largest = &num_list[0];
    for num in &num_list {
        if num > largest {
            largest = num;
        }
    }
    println!("The largest number is {}", largest);

    println!("The largest number is {}", get_largest(&num_list));
}

fn get_largest(num_list: &Vec<i32>) -> i32 {
    print_type(num_list);
    let mut largest = &num_list[0];
    for num in num_list {
        if largest < num {
            largest = num;
        }
    }
    *largest
}

fn print_type<T>(arg: &T) {
    println!("{:?}", std::any::type_name::<T>());
}
