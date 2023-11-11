// functions
use std::thread;

fn main() {
    // learn_functions();
    // learn_control_flow();
    learn_loops();
}

// repetition with loops
fn learn_loops() {
    // looping through a collection with for
    let arr = [10, 20, 30, 40, 50, 60, 70];
    for element in arr {
        println!("arr: {element}");
    }

    let mut index = 0;
    while  index < arr.len() {
        println!("arr[{index}]: {:?}", arr[index]);
        index += 1;
    }

    for num in (1..arr.len()).rev() {
        println!("arr:- {:?}", arr[num]);
    }



    // condition loops with `while`
    let mut num = 3;
    while num != 0 {
        println!("{num}");
        num -= 1;
    }


     // loop
    let mut idx = 0;
    loop {
        println!("Idx: {idx}");
        if idx % 2 == 0 {
            idx += 5;
            continue;
        }

        idx = idx + 1;
        if idx >= 50 {
            break
        }
    }

    // returning values from loops
    idx = 0;
    let loop_result  = loop {
        idx += 1;
        if idx == 10 {
            break idx * 2;
        }
    };

    println!("loop_result: {loop_result}");

    let mut i = 0;
    let mut j = 0;
    // loops with labels
    'outer_loop: loop {
        i += 1;
        if i == 3 {
            break 'outer_loop;
        }
        loop {
            println!("i: {i} - j: {j}");
            j += 1;
            if j == 3 {
                j = 0;
                continue 'outer_loop;
            }
        }
    }
}



// control flow
fn learn_control_flow() {
    let number:i128 = std::i128::MIN;
    if number > 0 {
        println!("{number} is a positive integer");
    } else if number == 0 {
        println!("{number} is zero");
    } else {
        println!("{number} is a negative integer");
    }

    // if in let
    let condition:bool = true;
    let th = if condition {9} else { 6 };
    println!("Threshold: {th}");
}


fn learn_functions() {
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