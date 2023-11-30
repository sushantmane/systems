//  To panic! or Not to panic!
fn main() {
    let secret_number = 120;
    let mut attempts = 10;
    loop {
        if attempts == 0 {
            println!("Exhausted all attempts!");
            return;
        }
        attempts -= 1;
        let mut guess = String::new();
        std::io::stdin().read_line(&mut guess);
        let guess: Guess = match guess.trim().parse() {
            Ok(n) => Guess::new(n),
            Err(_) => continue,
        };

       if guess.value() == secret_number {
           println!("won");
           return;
       }
    }
}


pub struct Guess {
    val: i32,
}

impl Guess {
    pub fn new(val: i32) -> Guess {
        if val < 1 || val > 100 {
            panic!("Guess value must be between 1 and 100, got {}.", val);
        }
        Guess { val }
    }

    pub fn value(&self) -> i32 {
        self.val
    }
}