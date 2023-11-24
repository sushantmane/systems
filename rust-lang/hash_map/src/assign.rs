use std::collections::HashMap;
pub fn get_median_and_mode(lst: &mut Vec<i32>) {
    lst.sort();

    println!("Sorted array: {:?}", lst);
    println!("Mode: {} Median: {}", get_mode(&lst), get_median(&lst));
}

fn get_median(lst: &Vec<i32>) -> i32 {
    if lst.len() == 0 {
        return -1;
    }

    let mid = lst.len() / 2;
    lst[mid]
}

fn get_mode(lst: &Vec<i32>) -> i32 {
    let mut frq = HashMap::new();
    let mut mode: Option<i32> = None;
    let mut mode_frq = 0;

    for &i in lst {
        let cnt = frq.entry(i).or_insert(0);
        *cnt += 1;

        if mode.is_none() {
            mode = Some(i);
        } else if *cnt >= mode_frq {
            mode_frq = *cnt;
            mode = Some(i);
        }
    }

    println!("Mode is {}. It appears {} times", mode.unwrap(), mode_frq);

    // for (k, v) in &frq {
    //     println!("{k} - {v}");
    // }

    // return mode val
    mode.unwrap()
}
