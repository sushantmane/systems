// Hash Map

use std::collections::HashMap;

mod assign;
mod learn;

mod assign1;

mod assign2;

fn main() {
    // learn::learn_hashmap();

    // let mut in_vec = vec![5, 3, 1, 1, 3, 3, 2, 2, 3, 6];
    // assign::get_median_and_mode(&mut in_vec);

    // let in_str = String::from("first apple orange juice");
    // assign1::convert_to_pig_latin(&in_str);

    let mut emp_db = HashMap::new();

    let queries = [
        "Add Sally to Engineering",
        "Add Amir to Sales",
        "Add Belly to Sales",
        "Add Roger to Engineering",
        "Add Tiger to Engineering",
    ];

    for query in queries {
        assign2::add_employee(&query, &mut emp_db);
    }
    println!(
        "Engineering - {:?}",
        assign2::get_emp_by_dept(&String::from("Engineering"), &emp_db)
    );
    println!(
        "Sales - {:?}",
        assign2::get_emp_by_dept(&String::from("Sales"), &emp_db)
    );
    println!("ALL - {:?}", assign2::get_all_people(&emp_db));
}
