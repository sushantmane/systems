fn main() {
    const NUM_HOURS_IN_DAY: u32 = 24;
    let mut x = 5;
    x = x + 1;
    println!("The value of x: {x}");
    let x = 64;

    let mut x = 8;
    let mut y = 18;
    {
        x = x + 1;
        let y = y + 1;
        println!("Inner value of x: {x} and y: {y}");
    }
    println!("The value of : {x} and y: {y}");

    let mut spaces = "  ";
    println!("spaces: {spaces}");
    let spaces = spaces.len();
    println!("spaces: {spaces}");
}
