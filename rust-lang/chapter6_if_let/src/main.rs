// control flow with if let

fn main() {
    let threshold = Some(3u8);
    // let threshold: Option<u8> = None;

    // regular
    match threshold {
        Some(v) => println!("Threshold is: {:?}", v),
        _ => (),
    }

    if let Some(v) = threshold {
        println!("The threshold is: {}", v);
    } else {
        println!("Magic land");
    }
}
