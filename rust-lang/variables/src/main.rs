fn main() {
    // Variables
    const NUM_HOURS_IN_DAY: u32 = 24;
    let mut x = 5;
    x = x + 1;
    println!("The value of x: {x}");
    let x = 64;

    let mut x = 8;
    let y = 18;
    {
        x = x + 1;
        let y = y + 1;
        println!("Inner value of x: {x} and y: {y}");
    }
    println!("The value of : {x} and y: {y}");

    let spaces = "  ";
    println!("spaces: {spaces}");
    let spaces = spaces.len();
    println!("spaces: {spaces}");



    // Data types
    let mut number: u32 = "43".parse().expect("Not a number!");
    println!("number: {number}");

    // Integer types
    let num_8bit: u8 = 118u8;
    println!("8-bit number: {num_8bit}");

    // signed 8-bit
    let num_signed_8bit = -128i8;
    println!("8-bit signed number: {num_signed_8bit}");

    let mut number: u16 = 512u16;
    println!("unsigned 16 bit number: {number}");


    let mut byte = b'A';
    println!("byte: {byte}");


    // Compound Types
    // tuple
    let tup: (i32, f64, u8) = (500, 6.4, 1);
    let (x, y, z) = tup;
    println!("tuple: {x}, {y}, and {z}");

    let first_val = tup.0;
    println!("tuple: {first_val}");
}
