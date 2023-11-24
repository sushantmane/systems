use std::collections::HashMap;

pub fn add_employee(query: &str, db: &mut HashMap<String, Vec<String>>) {
    let words: Vec<&str> = query.split_whitespace().collect();
    let emp = words[1];
    let dept = words[3];

    let emp_lst = db.entry(String::from(dept)).or_insert(Vec::new());
    emp_lst.push(String::from(emp));

    println!("emp: {emp} dept: {dept}");

    dbg!(&db);
}

pub fn get_emps_by_dept(dept: &str, db: &HashMap<String, Vec<String>>) -> Vec<String> {
    let res = db.get(&dept);
    let res = match res {
        Some(mut v) => {
            v.sort();
            v
        },
        None => Vec::new(),
    };
    return res;
}