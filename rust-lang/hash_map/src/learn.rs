// Hash Map

use std::collections::HashMap;

pub fn learn_hashmap() {
    let mut scores = HashMap::new();
    scores.insert(String::from("a1"), 10);
    scores.insert(String::from("b1"), 20);
    scores.insert(String::from("c1"), 30);


    dbg!(&scores);

    let team_name = String::from("a1");
    let score = scores.get(&team_name);

    match score {
        Some(v) => println!("{v}"),
        None => println!("NF"),
    };

    dbg!(&score);


    let score = scores.get(&team_name).copied().unwrap_or(0);
    println!("{score}");

    // iterator
    for (k, v) in &scores {
        println!("{k} - {v}");
    }


    // Hash Maps and Ownership
    let mut map = HashMap::new();
    let f1_name = String::from("fav color");
    let f1_val = String::from("Blue");
    map.insert(&f1_name, &f1_val); // <== borrowed
    println!("{f1_name} {f1_val}");

    // map.insert(f1_name, f1_val); // <== non allowed since map is HashMap<&String, &String> and not <String, String>


    let mut map = HashMap::new();
    let f1_name = String::from("fav color");
    let f1_val = String::from("Blue");
    map.insert(f1_name, f1_val); // <== ownership transfer
    // println!("{f1_name} {f1_val}"); <== illegal
    dbg!(&map);

    let mut color_scores = HashMap::new();
    // overwrite a old value
    color_scores.insert(String::from("Blue"), 10);
    color_scores.insert(String::from("Blue"), 25);
    dbg!(&color_scores);

    // putIfAbsent
    color_scores.entry(String::from("Yellow")).or_insert(20);
    color_scores.entry(String::from("Blue")).or_insert(30);
    dbg!(&color_scores);

    // compute
    let text = "hello world wonderful world";
    let mut freq = HashMap::new();
    for word in text.split_whitespace() {
        let cnt = freq.entry(word).or_insert(0);
        *cnt += 1;
    }
    dbg!(&freq);
}
