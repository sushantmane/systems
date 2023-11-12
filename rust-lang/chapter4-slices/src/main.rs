// The Slice Type

fn main() {
    let mut s= String::from("Hello, world!");
    let end_idx_of_first_word = find_first_word(&s);
    println!("first word in {} is {}", s, end_idx_of_first_word);
    // let substring =  &s[0..end_idx_of_first_word];
    // println!("substring: {}", substring);

    let slice_1 = &s[..5];
    let slice_2 = &s[5..];
    let slice_100_percent = &s[..];
    println!("slice_1: {}", slice_1);
    println!("slice_2: {}", slice_2);
    println!("slice_100%: {}", slice_100_percent);


    let slice_11 = first_word(&s); // <-- immutable borrow
    // s.clear(); <-- mutable borrow; not allowed
    println!("refactored- first_word: {}", slice_11);
    println!("refactored- second_word: {}", second_word(&s));
    s.clear();
    // println!("slice_1: {}", slice_1);

}

fn first_word(s: &String) -> &str {
    let bytes = s.as_bytes();
    for (idx, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[..idx]
        }
    }
    &s[..]
}


fn second_word(s: &String) -> &str {
    let bytes = s.as_bytes();
    for (idx, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[idx + 1..]
        }
    }
    &s[..]
}


fn find_first_word(s: &String) -> usize {
    let bytes = s.as_bytes();
    for (idx, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return idx;
        }
    }
    s.len()
}
