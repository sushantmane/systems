use std::fmt::format;

pub fn convert_to_pig_latin(in_str: &str) {
    let mut res = String::new();
    for word in in_str.split_whitespace() {
        if word.starts_with("a")
            || word.starts_with("e")
            || word.starts_with("i")
            || word.starts_with("o")
            || word.starts_with("u")
        {
            res.push_str(&format!("{word}-hay"));
        } else {
            res.push_str(&format!("{}-{}ay", &word[1..], &word[0..1]));
        }
        res.push_str(" ");
    }
    println!("{res}");
}
