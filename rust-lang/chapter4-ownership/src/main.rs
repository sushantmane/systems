// Chapter 4: Ownership

fn main() {
    let s1 = give_ownership();
    let s1 = the_lord_giveth_and_the_lord_taketh_away(s1);
    take_ownership(s1);
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
