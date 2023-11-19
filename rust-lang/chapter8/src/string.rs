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
}