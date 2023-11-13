// Chapter 05: Structs

fn main() {
    let username = String::from("baymax");

    let mut user_baymax = User {
        active: true,
        sign_in_count: 2,
        username: username,
        email: String::from("baymax@example.com")
    };

    user_baymax.username = String::from("Baymax");

    let user_baymax = build_user(String::from("baymax"));

    let user_baymax = build_user_with_field_init_shorthand(String::from("BayMax"));

    // regular update
    let user_baymax_copy = User {
        username:  String::from("baymax_copy"),
        email: user_baymax.email,
        active: user_baymax.active,
        sign_in_count: user_baymax.sign_in_count
    };

    display_user(&user_baymax_copy);

    println!("### Struct update syntax demo ###");
    // struct update syntax
    let user_with_update_syntax = User {
        email: String::from("baymax@internet.com"),
        ..user_baymax_copy
    };
    display_user(&user_with_update_syntax);

    display_user(&user_baymax_copy);
}

fn display_user(user: &User) {
    println!("{}", user.active);
    println!("{}", user.sign_in_count);
    println!("{}", user.username);
    println!("{}", user.email);
}


fn build_user_with_field_init_shorthand(username: String) -> User {
    let mut email = String::from(username.clone());
    email.push_str("@example.com");
    User {
        username,
        email,
        active: true,
        sign_in_count: 4
    }
}

fn build_user(username: String) -> User {
    let mut email = String::from(username.clone());
    email.push_str("@example.com");
    User {
        username: username,
        email: email,
        active: true,
        sign_in_count: 4
    }
}

struct User {
    active: bool,
    username: String,
    email: String,
    sign_in_count: u64
}