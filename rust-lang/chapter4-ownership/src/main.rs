// Chapter 4: Ownership

fn main() {
    let s1 = give_ownership();
    let mut s1 = the_lord_giveth_and_the_lord_taketh_away(s1);
    // take_ownership(s1);

    let len = calculate_length(&mut s1);

    println!("The length of string \"{}\" is {}", s1, len);

    let mut s1 = String::from("hello!");

    let r2 = &s1;
    let r1 = &mut s1;
    println!("r1: {}", r1);


    let reference_to_nothing = dangle();
    println!("dangle: {}", reference_to_nothing);
}

// dangling references
fn dangle() -> String {
    let s = String::from("hello");
    s
}


// references and borrowing
fn calculate_length(s: &mut String) -> usize {
    s.push_str(" - append");
    s.len()
}




fn give_ownership() -> String {
    let some_string = String::from("The lord giveth and the lord taketh away");
    some_string
}


fn take_ownership(s: String) {
    println!("s: {}", s);
}

fn the_lord_giveth_and_the_lord_taketh_away(a_string: String) -> String {
    a_string
}



