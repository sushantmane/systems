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

pub fn get_emp_by_dept(dept: &str, db: &HashMap<String, Vec<String>>) -> Vec<String> {
    let val = db.get(dept);
    let res = match val {
        Some(mut v) => {
            let mut vc = v.clone();
            vc.sort();
            vc
        }
        None => Vec::new(),
    };

    res
}

pub fn get_all_people(db: &HashMap<String, Vec<String>>) -> Vec<Vec<String>> {
    let mut res: Vec<Vec<String>> = vec![];
    let mut keys: Vec<String> = vec![];
    for key in db.keys() {
        keys.push(String::from(key));
    }
    keys.sort();

    for key in keys {
        let val = db.get(&key).unwrap();
        let mut val_cp = val.clone();
        val_cp.sort();
        res.push(val_cp);
    }

    res
}
