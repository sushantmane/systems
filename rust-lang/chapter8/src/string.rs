use std::fmt::format;

// strings are implemented as a collection of bytes
pub fn string_basics() {

    // create a new string
    let mut s1 = String::new();
    s1.push('c');
    dbg!(&s1);


    let data = "initial content";
    let s2= data.to_string();
    dbg!(&s2);
    dbg!(&data);

    let s3 = String::from("xy");
    dbg!(&s3);

    let mut namaste = String::from("नमस्ते");
    dbg!(&namaste);

    let mut ms1 = String::from("mutable_string");
    ms1.push_str("-mutable");
    dbg!(&ms1);
    let other_half = "-str";
    let ms2 = ms1 + "-2-" + &other_half;
    dbg!(&ms2);
    dbg!(&other_half);
    // dbg!(&ms1); <-- invalid

    // format!
    let fs1 = format!("{}-{}", &ms2, &other_half);
    dbg!(&fs1);
    dbg!(ms2);

    namaste.push('-');
    dbg!(&namaste);


    // multiple-string concat
    let s1 = String::from("tic");
    let s2 = String::from("tac");
    let s3 = String::from("toe");
    let s123 = s1 + "-" + &s2 + "-" + &s3;
    println!("s123: {:?}", &s123);
    let s23f = format!("{s2}-{s3}");
    println!("s23f: {:?}", &s23f);
}


pub fn string_indexing() {

}