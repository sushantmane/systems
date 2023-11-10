// functions
use std::thread;

fn main() {
    let thread_id = thread::current().id();
    println!("Hello, world! - thread_id: {:?}", thread_id);

    let thread_1 = thread::spawn(|| test_function());
    thread_1.join().expect("Failed to join threads");
    let thread_id = thread::current().id();
    println!("back to main function - thread_id: {:?}", thread_id);

    function_with_parameters(121, 3.14, 'a');


    let block_val = {
        let x = 3;
        x + 1
    };

    println!("block_val: {block_val}");


    println!("function with return value: {}", fun_with_return_val());
}


fn test_function() {
    println!("Inside test function....");
    let thread_id = thread::current().id();
    println!("test_function - thread_id: {:?}", thread_id);
}


fn function_with_parameters(x: i8, y: f32, c: char) {
    println!("Value of x: {x}, y:{y}, c: {c}");
}

fn fun_with_return_val() -> i32 {
    89
}