//  The match Control Flow Construct

fn main() {
    let coins = [Coin::Quarter(UsState::California), Coin::Quarter(UsState::Washington), Coin::Penny, Coin::Dime, Coin::Nickel];
    for coin in coins {
        println!("{}", value_in_cents(coin));
    }


    // Matching with Option<T>
    let val =  add_one(Some(6));
    println!("test: add_one - {:?}", val);
    println!("test: add_one - {:?}", add_one(val));
    println!("test: add_one - {:?}", add_one(Option::None));

    // Catch-all patterns
    let dice_roll = 91;
    match dice_roll {
        3 => println!("blah!"),
        9 => println!("blah!blah!"),
        other => println!("blah!blah!blah! : {}", other),
    }

    // without data binding, i, e, don't want to use value
    match dice_roll {
        3 => println!("blah!"),
        9 => println!("blah!blah!"),
        _ => println!("blah!blah!blah!"),
    }
    dbg!(dice_roll);

    // nothing happens - unit value/empty tuple
    match dice_roll {
        3 => println!("blah!"),
        9 => println!("blah!blah!"),
        _ => (),
    }

}

fn add_one(x: Option<i32>) -> Option<i32> {
    match x {
        Some(n) => Option::Some(n + 1),
        None => None,
    }
}


fn value_in_cents(coin: Coin) -> f64 {
    match coin {
        Coin::Penny => {
            println!("penny is here!");
            1f64
        }
        Coin::Nickel => 5f64,
        Coin::Dime => 10f64,
        Coin::Quarter(state) => {
            match state {
                UsState::California => 25f64 - 25f64 * 0.0925f64,
                UsState::NewYork => 25f64,
                UsState::Washington => 25f64,
            }
        }
    }
}

#[derive(Debug)]
enum UsState {
    California,
    Washington,
    NewYork,
}

enum Coin {
    Penny,
    Nickel,
    Dime,
    Quarter(UsState),
}