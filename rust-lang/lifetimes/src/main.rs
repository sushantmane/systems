fn main() {
    // Validating references with lifetimes

    let r;

    {
        let x = 5;
        r = &x; // error
    }

    println!("r: {}", r);
}
