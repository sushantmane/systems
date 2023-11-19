pub fn vector_experiments() {

    let mut vector: Vec<i32> = Vec::new();
    // vector.push(123);
    // match vector.pop() {
    //     Some(v) => println!("val is: {}", v),
    //     _ => println!("val not found"),
    // }
    vector.push(1);
    vector.push(2);
    vector.push(3);
    dbg!(&vector);

    let mut v1 = vec![1, 2, 3];
    v1.push(5);
    v1.push(6);
    dbg!(&v1);


    // reading
    let idx_3_val = &vector[2];
    println!("vector[2]: {}", idx_3_val);


    let third = v1.get(2);
    match third {
        Some(t) => println!("third: {t}"),
        None => println!("there is no third element"),
    }


    let mut v = vec![1, 2, 3, 4, 5];
    let first = &v[0];
    v.push(6);
    // println!("1st: {first}"); <-- invalid
    for i in &v {
        println!("{i}");
    }

    // mutable
    for i in &mut v {
        *i += 50;
        println!("{i}");
    }

    #[derive(Debug)]
    enum Cell {
        Int(i32),
        Float(f64),
        Text(String),
    }

    let row = vec![Cell::Int(1),
                   Cell::Text(String::from("test")),
                   Cell::Float(10.12)];
    for r in &row {
        println!("r: {:?}", r);
    }

    let mut vec1: Vec<Cell> = vec![];
    vec1.pop();
    println!("{:?}", vec1);
}