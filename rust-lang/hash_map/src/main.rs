// Hash Map

use std::collections::HashMap;

mod learn;
mod assign;

mod assign1;

mod assign2;

fn main() {

    // learn::learn_hashmap();

    // let mut in_vec = vec![5, 3, 1, 1, 3, 3, 2, 2, 3, 6];
    // assign::get_median_and_mode(&mut in_vec);


    // let in_str = String::from("first apple orange juice");
    // assign1::convert_to_pig_latin(&in_str);

    let mut emp_db = HashMap::new();

    let queries = ["Add Sally to Engineering",
        "Add Amir to Sales",
        "Add Belly to Sales",
        "Add Roger to Engineering",
        "Add Tiger to Engineering"
    ];

    for query in queries {
        assign2::add_employee(&query, &mut emp_db);
    }
}
