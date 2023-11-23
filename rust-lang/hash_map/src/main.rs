// Hash Map

use std::collections::HashMap;

mod learn;
mod assign;

fn main() {

    // learn::learn_hashmap();

    let mut in_vec = vec![5, 3, 1, 1, 3, 3, 2, 2, 3, 6];
    assign::get_median_and_mode(&mut in_vec);
}
